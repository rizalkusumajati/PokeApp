package com.example.pokeapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokeapp.TestCoroutineRule
import com.example.pokeapp.data.repository.PokemonRepository
import com.example.pokeapp.domain.models.Pokemon
import com.example.pokeapp.domain.models.Pokemons
import com.example.pokeapp.domain.models.UIState
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainMenuViewModelTest {
    @get:Rule
    val testInstantTaskRules: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @MockK
    private lateinit var repository: PokemonRepository

    private lateinit var mainMenuViewModel: MainMenuViewModel

    @MockK
    private lateinit var uiStateMovieListObserver: Observer<UIState<Pokemons>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetPokemonLoading() {
        //given
        val loadingValue = UIState.Loading
        val captureData: MutableList<UIState<Pokemons>> = mutableListOf()
        testCoroutineRule.runBlockingTest {
            val outputFlow: Flow<UIState<Pokemons>> = flow {
                emit(loadingValue)
            }
            coEvery { repository.getPokemon() } returns outputFlow
            mainMenuViewModel = MainMenuViewModel(repository)
            mainMenuViewModel.listPokemon.observeForever(uiStateMovieListObserver)
            //when
            mainMenuViewModel.getPokemon()

            //then
            verify(
                verifyBlock = { uiStateMovieListObserver.onChanged(capture(captureData)) },
                atLeast = 1
            )
            Truth.assertThat(captureData[0]).isInstanceOf(UIState.Loading::class.java)
            Truth.assertThat(captureData[0]).isEqualTo(loadingValue)
            Truth.assertThat(mainMenuViewModel.listPokemon.value).isEqualTo(loadingValue)
        }
    }

    @Test
    fun testGetPokemonError() {
        //given
        val errorValue = UIState.Error("error")
        val captureData: MutableList<UIState<Pokemons>> = mutableListOf()
        testCoroutineRule.runBlockingTest {
            val outputFlow: Flow<UIState<Pokemons>> = flow {
                emit(errorValue)
            }
            coEvery { repository.getPokemon() } returns outputFlow
            mainMenuViewModel = MainMenuViewModel(repository)
            mainMenuViewModel.listPokemon.observeForever(uiStateMovieListObserver)
            //when
            mainMenuViewModel.getPokemon()

            //then
            verify(
                verifyBlock = { uiStateMovieListObserver.onChanged(capture(captureData)) },
                atLeast = 1
            )
            Truth.assertThat(captureData[0]).isInstanceOf(UIState.Error::class.java)
            Truth.assertThat(captureData[0]).isEqualTo(errorValue)
            Truth.assertThat(mainMenuViewModel.listPokemon.value).isEqualTo(errorValue)
        }
    }

    @Test
    fun testGetPokemonSuccess() {
        //given
        val pokemon =
            Pokemon(
                id = 1,
                name = "bulbasaur",
                urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                frontShinny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            )
        val pokemons = listOf<Pokemon>(
            pokemon, pokemon, pokemon, pokemon
        )
        val successValue = UIState.SuccessFromRemote(pokemons)
        val captureData: MutableList<UIState<Pokemons>> = mutableListOf()
        testCoroutineRule.runBlockingTest {
            val outputFlow: Flow<UIState<Pokemons>> = flow {
                emit(successValue)
            }
            coEvery { repository.getPokemon() } returns outputFlow
            mainMenuViewModel = MainMenuViewModel(repository)
            mainMenuViewModel.listPokemon.observeForever(uiStateMovieListObserver)
            //when
            mainMenuViewModel.getPokemon()

            //then
            verify(
                verifyBlock = { uiStateMovieListObserver.onChanged(capture(captureData)) },
                atLeast = 1
            )
            Truth.assertThat(captureData[0]).isInstanceOf(UIState.SuccessFromRemote::class.java)
            Truth.assertThat(captureData[0]).isEqualTo(successValue)
            Truth.assertThat(mainMenuViewModel.listPokemon.value).isEqualTo(successValue)
        }
    }

}
