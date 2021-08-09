package com.example.pokeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.datasource.emptyDataSource
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentMainMenuBinding
import com.example.pokeapp.domain.models.Pokemon
import com.example.pokeapp.domain.models.Pokemons
import com.example.pokeapp.domain.models.Type
import com.example.pokeapp.domain.models.UIState
import com.example.pokeapp.util.loadImageUrl
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MainMenuFragment : Fragment() {
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    private val mainMenuViewModel: MainMenuViewModel by viewModels()
    private val pokemonDataSource = emptyDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDisplay()
        setupObserver()
    }

    private fun setupObserver() {
        mainMenuViewModel.listPokemon.observe(viewLifecycleOwner) { uiState ->

            when (uiState) {
                UIState.Empty -> Log.d("MOVIE", "empty")
                is UIState.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    uiState.showToast(requireContext())
                }
                UIState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    Log.d("MOVIE", "Loading")
                }
                is UIState.SuccessFromRemote -> {

                    with(uiState.getData()) {
                        getDetailData(this)
                    }
                }
            }
        }

        mainMenuViewModel.listDetailPokemon.observe(viewLifecycleOwner) {
            binding.progressCircular.visibility = View.GONE
            pokemonDataSource.set(it)
        }
    }

    private fun getDetailData(pokemons: Pokemons) {
        mainMenuViewModel.getPokemonDetail(pokemons)
        Log.d("TAG", "All Result")
    }

    private fun setupDisplay() {
        binding.rvPokemon.setup {
            withLayoutManager(LinearLayoutManager(requireContext()))
            withDataSource(pokemonDataSource)
            withItem<Pokemon, MainMenuViewHolder>(R.layout.item_list_pokemon) {
                onBind(::MainMenuViewHolder) { _, item ->
                    tvPokemon.text = item.name.capitalize()
                    ivPhoto.loadImageUrl(item.urlImage)

                    layoutBadge.setup {
                        withDataSource(dataSourceOf(item.type))
                        withLayoutManager(
                            LinearLayoutManager(
                                requireContext(),
                                RecyclerView.HORIZONTAL,
                                false
                            )
                        )
                        withItem<Type, TypeViewHolder>(R.layout.item_list_type) {
                            onBind(::TypeViewHolder) { _, itemType ->
                                tvType.text = itemType.nameType.capitalize()

                            }
                        }
                    }


                }.onClick {
                    val action = MainMenuFragmentDirections.goToDetail(item.name)
                    findNavController().navigate(action)
                }
            }

        }
    }


}
