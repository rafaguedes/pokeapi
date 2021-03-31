package br.com.yourapp.pokeapi.screens.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.interfaces.OnInitialLoadCallback
import br.com.yourapp.pokeapi.interfaces.OnNextPageLoadCallback
import br.com.yourapp.pokeapi.models.Pokemon
import br.com.yourapp.pokeapi.screens.details.PokemonDetailsFragment
import br.com.yourapp.pokeapi.screens.list.PokemonListContract.Presenter
import br.com.yourapp.pokeapi.screens.list.adapters.PokemonListAdapter
import br.com.yourapp.pokeapi.utils.ActivityUtils.showMessageDialog
import butterknife.BindView
import butterknife.ButterKnife
import com.airbnb.lottie.LottieAnimationView
import dagger.internal.Preconditions

class PokemonListFragment() : Fragment(), PokemonListContract.View {
    @JvmField
    @BindView(R.id.pokemonList)
    var pokemonList: RecyclerView? = null

    @JvmField
    @BindView(R.id.lottieAnimationView)
    var lottieAnimationView: LottieAnimationView? = null
    var presenter: Presenter? = null

    private var pokemonListAdapter: PokemonListAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var isLoadingNextPage = false

    companion object {
        @JvmStatic
        fun newInstance(): PokemonListFragment? {
            return PokemonListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        ButterKnife.bind(this, view)
        gridLayoutManager = GridLayoutManager(context, 1)
        presenter?.initialLoad(onInitialLoadCallback)

        pokemonList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isLoadingNextPage) {
                        isLoadingNextPage = true
                        lottieAnimationView!!.alpha = 0f
                        lottieAnimationView!!.visibility = View.VISIBLE
                        lottieAnimationView!!.animate().alpha(1f).setDuration(300).withEndAction { presenter!!.loadNextPage(onNextPageLoadCallback) }.start()
                    }
                }
            }
        })
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun getCurrentLayoutManager(): GridLayoutManager? {
        return gridLayoutManager
    }

    override fun context(): Context? {
        return context
    }

    override fun setCurrentPresenter(presenter: Presenter?) {
        this.presenter = Preconditions.checkNotNull(presenter)
    }

    var onInitialLoadCallback: OnInitialLoadCallback = object : OnInitialLoadCallback {

        override fun onLoad(pokemonList: List<Pokemon?>?) {
            pokemonListAdapter = PokemonListAdapter(context()!!, pokemonList as ArrayList<Pokemon?>?)
            this@PokemonListFragment.pokemonList?.adapter = pokemonListAdapter
            this@PokemonListFragment.pokemonList?.layoutManager = gridLayoutManager
        }

        override fun onError(error: String?) {
            showMessageDialog(context()!!, "An error occurred!")
        }
    }

    var onNextPageLoadCallback: OnNextPageLoadCallback = object : OnNextPageLoadCallback {

        override fun onLoad(pokemonList: List<Pokemon?>?) {
            lottieAnimationView!!.alpha = 1f
            lottieAnimationView!!.visibility = View.VISIBLE
            lottieAnimationView!!.animate().alpha(0f).setDuration(300).withEndAction {
                pokemonListAdapter!!.add(pokemonList as List<Pokemon>)
                isLoadingNextPage = false
                lottieAnimationView!!.visibility = View.GONE
            }.start()
        }

        override fun onError(error: String?) {
            showMessageDialog(context()!!, "An error occurred!")
        }
    }

}