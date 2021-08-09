package com.example.pokeapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.afollestad.recyclical.datasource.emptyDataSource
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentEvolutionBinding
import com.example.pokeapp.domain.models.EvolutionManipulation
import com.example.pokeapp.util.loadImageUrl


/**
 * A simple [Fragment] subclass.
 * Use the [EvolutionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EvolutionFragment : Fragment() {
    private var _binding: FragmentEvolutionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels({ requireParentFragment() })
    private val evolutionDataSource = emptyDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDisplay()
        setupObserver()
        viewModel.getEvolutionById(viewModel.pokemon.value?.idEvolution ?: 0)
    }

    private fun setupDisplay() {
        binding.rvEvolution.setup {
            withDataSource(evolutionDataSource)
            withItem<EvolutionManipulation, EvolutionViewHolder>(R.layout.item_list_evolution) {
                onBind(::EvolutionViewHolder) { _, item ->
                    ivPhoto1.loadImageUrl(item.urlEvolution1)
                    ivPhoto2.loadImageUrl(item.urlEvolution2)
                    tvPokemonName1.text = item.nameEvolution1.capitalize()
                    tvPokemonName2.text = item.nameEvolution2.capitalize()
                    tvMinLevel.text = "Lv. ${item.minLevel}"
                }
            }
        }
    }

    private fun setupObserver() {
        viewModel.evolution.observe(viewLifecycleOwner) { evolutions ->
            val listEvolve = arrayListOf<EvolutionManipulation>()
            for (i in 0..evolutions.lastIndex - 1) {
                listEvolve.add(
                    EvolutionManipulation(
                        nameEvolution1 = evolutions[i].nameEvolution,
                        nameEvolution2 = evolutions[i + 1].nameEvolution,
                        urlEvolution1 = evolutions[i].urlImage,
                        urlEvolution2 = evolutions[i + 1].urlImage,
                        minLevel = evolutions[i + 1].minLevel
                    )
                )
            }
            evolutionDataSource.set(listEvolve)
        }
    }

}

fun Fragment.replaceEvolutionFragment(
    resId: Int
) {
    val evolutionFragment = EvolutionFragment()
    childFragmentManager.beginTransaction()
        .replace(resId, evolutionFragment).commit()
}
