package com.example.pokeapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.pokeapp.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    //database
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokeapp"
        ).build()

    //dao
    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.pokemonDao()
    @Singleton
    @Provides
    fun provideEvolutionDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.evolutionDao()
}
