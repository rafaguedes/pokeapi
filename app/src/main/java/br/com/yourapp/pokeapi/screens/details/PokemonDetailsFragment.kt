package br.com.yourapp.pokeapi.screens.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.yourapp.pokeapi.R
import br.com.yourapp.pokeapi.models.Pokemon
import br.com.yourapp.pokeapi.screens.list.PokemonListFragment
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import dagger.internal.Preconditions

class PokemonDetailsFragment : Fragment(), PokemonDetailsContract.View {
    var presenter: PokemonDetailsContract.Presenter? = null
    private var pokemon: Pokemon? = null

    @JvmField
    @BindView(R.id.imageView)
    var imageView: ImageView? = null

    @JvmField
    @BindView(R.id.toolbar)
    var toolbar: Toolbar? = null

    @JvmField
    @BindView(R.id.txtName)
    var txtName: TextView? = null

    @JvmField
    @BindView(R.id.txtHeight)
    var txtHeight: TextView? = null

    @JvmField
    @BindView(R.id.txtWeight)
    var txtWeight: TextView? = null

    @JvmField
    @BindView(R.id.txtOrder)
    var txtOrder: TextView? = null

    @JvmField
    @BindView(R.id.content)
    var content: LinearLayout? = null

    @JvmField
    @BindView(R.id.abilitiesList)
    var abilitiesList: RecyclerView? = null

    @JvmField
    @BindView(R.id.txtLoadingAbilities)
    var txtLoadingAbilities: TextView? = null

    @JvmField
    @BindView(R.id.typesList)
    var typesList: RecyclerView? = null

    @JvmField
    @BindView(R.id.txtLoadingTypes)
    var txtLoadingTypes: TextView? = null
    var slideUp: Animation? = null
    var slideDown: Animation? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        ButterKnife.bind(this, view)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        slideUp = AnimationUtils.loadAnimation(context(), R.anim.slide_up)
        slideDown = AnimationUtils.loadAnimation(context(), R.anim.slide_down)
        content!!.startAnimation(slideUp)
        val intent = activity!!.intent
        pokemon = intent.getSerializableExtra("pokemon") as Pokemon
        txtName!!.text = pokemon!!.name?.toUpperCase()
        txtHeight!!.text = pokemon!!.pokemonInfo?.height.toString() + " inches"
        txtWeight!!.text = pokemon!!.pokemonInfo?.weight.toString() + " lbs"
        txtOrder!!.text = pokemon!!.pokemonInfo?.order.toString() + ""
        Picasso.get()
                .load(pokemon!!.pokemonInfo?.sprites?.other?.official_artwork?.front_default)
                .placeholder(R.drawable.picture_placeholder)
                .error(R.drawable.error_placeholder)
                .into(imageView)
        presenter!!.loadPokemonAbilities(pokemon!!.pokemonInfo?.abilities, abilitiesList, txtLoadingAbilities)
        for (type in pokemon!!.pokemonInfo?.types!!) {
            presenter!!.loadPokemonTypes(type.type, typesList, txtLoadingTypes)
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setCurrentPresenter(presenter: PokemonDetailsContract.Presenter?) {
        this.presenter = Preconditions.checkNotNull(presenter)
    }

    override fun context(): Context? {
        return context
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                content!!.startAnimation(slideDown)
                activity!!.supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onBackPressed() {
        content!!.startAnimation(slideDown)
    }

    companion object {
        @JvmStatic
        fun newInstance(): PokemonDetailsFragment? {
            return PokemonDetailsFragment()
        }
    }
}