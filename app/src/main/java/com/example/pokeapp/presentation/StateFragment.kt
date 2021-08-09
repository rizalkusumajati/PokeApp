package com.example.pokeapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.recyclical.datasource.emptyDataSource
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentStateBinding
import com.example.pokeapp.domain.models.Ability
import com.example.pokeapp.domain.models.Stat
import com.example.pokeapp.util.loadImageUrl
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [StateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class StateFragment : Fragment() {
    private var _binding: FragmentStateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels({ requireParentFragment() })
    val statDataSource = emptyDataSource()
    val abilityDataSource = emptyDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = (requireParentFragment() as DetailFragment).args
        setupDisplay()
        setupObserver()
        viewModel.getPokemonByName(args.name)
    }

    private fun setupDisplay() {
        binding.rvStat.setup {
            withLayoutManager(LinearLayoutManager(requireContext()))
            withDataSource(statDataSource)
            withItem<Stat, StatBasicViewHolder>(R.layout.item_list_stat_basic) {
                onBind(::StatBasicViewHolder) { _, item ->
                    var nameDisplay = item.nameStat
                    if (item.nameStat.contains("special-")) {
                        nameDisplay = item.nameStat.replace("special-", "sp")
                    }
                    tvStatName.text = "$nameDisplay"
                    progress.progress = item.baseStat
                }
            }
        }

        binding.rvAbility.setup {
            withLayoutManager(LinearLayoutManager(requireContext()))
            withDataSource(abilityDataSource)
            withItem<Ability, AbilityViewHolder>(R.layout.item_list_ability) {
                onBind(::AbilityViewHolder) { _, item ->
                    tvTitleAbility.text = item.nameAbility.capitalize()
                    tvDescAbility.text = item.descAbility
                }
            }
        }
    }

    private fun setupObserver() {
        viewModel.pokemon.observe(viewLifecycleOwner) {
            statDataSource.set(it.stat)
            abilityDataSource.set(it.ability)
            binding.ivFrontDefault.loadImageUrl(it.frontDefault)
            binding.ivFrontShinny.loadImageUrl(it.frontShinny)
        }
    }
}

fun Fragment.replaceStatFragment(
    resId: Int
) {
    val stateFragment = StateFragment()
    childFragmentManager.beginTransaction()
        .replace(resId, stateFragment).commit()
}
