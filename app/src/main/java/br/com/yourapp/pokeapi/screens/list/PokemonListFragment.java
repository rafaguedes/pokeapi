package br.com.yourapp.pokeapi.screens.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

import br.com.yourapp.pokeapi.R;
import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback;
import br.com.yourapp.pokeapi.interfaces.OnNextPageLoadCallback;
import br.com.yourapp.pokeapi.models.Pokemon;
import br.com.yourapp.pokeapi.screens.list.adapters.PokemonListAdapter;
import br.com.yourapp.pokeapi.utils.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonListFragment extends Fragment implements PokemonListContract.View {

    @BindView(R.id.pokemonList)
    public RecyclerView pokemonList;

    @BindView(R.id.lottieAnimationView)
    public LottieAnimationView lottieAnimationView;

    public PokemonListContract.Presenter presenter;

    private PokemonListAdapter pokemonListAdapter;
    private GridLayoutManager gridLayoutManager;

    private boolean isLoadingNextPage = false;

    public static PokemonListFragment newInstance() {
        PokemonListFragment pokemonListFragment = new PokemonListFragment();
        return pokemonListFragment;
    }

    @Override
    public void setPresenter(PokemonListContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        gridLayoutManager = new GridLayoutManager(getContext(), 1);

        presenter.initialLoad(onInitialLoadCallback);

        pokemonList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(!isLoadingNextPage) {
                        isLoadingNextPage = true;
                        lottieAnimationView.setAlpha(0f);
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        lottieAnimationView.animate().alpha(1f).setDuration(300).withEndAction(() -> {
                            presenter.loadNextPage(onNextPageLoadCallback);
                        }).start();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public GridLayoutManager getLayoutManager() {
        return gridLayoutManager;
    }

    public OnInitialLoadCallback onInitialLoadCallback = new OnInitialLoadCallback() {
        @Override
        public void onLoad(List<Pokemon> pokemonList) {
            pokemonListAdapter = new PokemonListAdapter(context(), (ArrayList) pokemonList);
            PokemonListFragment.this.pokemonList.setAdapter(pokemonListAdapter);
            PokemonListFragment.this.pokemonList.setLayoutManager(gridLayoutManager);
        }

        @Override
        public void onError(String error) {
            ActivityUtils.showMessageDialog(context(), "An error occurred!");
        }
    };

    public OnNextPageLoadCallback onNextPageLoadCallback = new OnNextPageLoadCallback() {
        @Override
        public void onLoad(List<Pokemon> pokemonList) {
            lottieAnimationView.setAlpha(1f);
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.animate().alpha(0f).setDuration(300).withEndAction(() -> {
                pokemonListAdapter.add(pokemonList);
                isLoadingNextPage = false;
                lottieAnimationView.setVisibility(View.GONE);
            }).start();
        }

        @Override
        public void onError(String error) {
            ActivityUtils.showMessageDialog(context(), "An error occurred!");
        }
    };
}
