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
import br.com.yourapp.pokeapi.interfaces.OnImageClickedCallback;
import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback;
import br.com.yourapp.pokeapi.interfaces.OnPageLoadCallback;
import br.com.yourapp.pokeapi.models.Pokemon;
import br.com.yourapp.pokeapi.screens.list.adapters.PokemonListAdapter;
import br.com.yourapp.pokeapi.utils.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

public class PokemonListFragment extends Fragment implements PokemonListContract.View {

    @BindView(R.id.galleryList)
    public RecyclerView galleryList;

    @BindView(R.id.lottieAnimationView)
    public LottieAnimationView lottieAnimationView;

    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;

    public PokemonListContract.Presenter presenter;
    private int currentPage = 1;

    private PokemonListAdapter pokemonListAdapter;

    private GridLayoutManager gridLayoutManager;

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
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);

        gridLayoutManager = new GridLayoutManager(getContext(), 1);

        presenter.initialLoad(onInitialLoadCallback);

        galleryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    currentPage++;

                    lottieAnimationView.setAlpha(0f);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.animate().alpha(1f).setDuration(300).start();

                    presenter.loadPage(currentPage, new OnPageLoadCallback() {
                        @Override
                        public void onLoad(List<Pokemon> pokemonList) {
                            pokemonListAdapter.addPage(pokemonList);
                            pokemonListAdapter.notifyDataSetChanged();
                            lottieAnimationView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(String error) {
                            ActivityUtils.showMessageDialog(context(), "An error occurred!");
                            lottieAnimationView.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
        pullToRefresh.setOnRefreshListener(() -> presenter.initialLoad(onInitialLoadCallback));

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
            galleryList.setAdapter(pokemonListAdapter);
            galleryList.setLayoutManager(gridLayoutManager);
            pullToRefresh.setRefreshing(false);
        }

        @Override
        public void onError(String error) {
            ActivityUtils.showMessageDialog(context(), "An error occurred!");
            pullToRefresh.setRefreshing(false);
        }
    };
}
