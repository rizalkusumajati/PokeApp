package com.example.pokeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokeapp.databinding.FragmentDetailBinding
import com.example.pokeapp.domain.models.UIState
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()
    val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDisplay()
        setupObserver()
        viewModel.getPokemonSpecies(args.name)
    }

    private fun setupObserver() {
        viewModel.detailPokemon.observe(viewLifecycleOwner) { uiState ->

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
                        viewModel.getPokemonEvolution(this.idEvolution, this.name)
                    }
                }
            }
        }

        viewModel.detailPokemonEvolution.observe(viewLifecycleOwner) { uiState ->

            when (uiState) {
                UIState.Empty -> Log.d("MOVIE", "empty")
                is UIState.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    uiState.showToast(requireContext())
                }
                UIState.Loading -> {
                    Log.d("MOVIE", "Loading")
                }
                is UIState.SuccessFromRemote -> {
                    with(uiState.getData()) {
                        binding.progressCircular.visibility = View.GONE
                        replaceStatFragment(binding.container.id)
                    }
                }
            }
        }
    }

    private fun setupDisplay() {
        binding.toolbar.title = args.name.capitalize()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        with(binding.tabDetail) {
            addTab(this.newTab().setText("Stat"))
            addTab(this.newTab().setText("Evolution"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> replaceStatFragment(binding.container.id)
                        1 -> replaceEvolutionFragment(binding.container.id)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            }

            )

        }

    }


}
