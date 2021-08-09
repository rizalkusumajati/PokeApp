package com.example.pokeapp.data.local.dao

import androidx.room.*
import com.example.pokeapp.data.local.entity.*

@Dao
interface PokemonDao : BaseDao<PokemonEntity> {
    @Transaction
    suspend fun replaceAll(pokemonEntities: PokemonEntities) {
        deleteAll()
        insertAll(pokemonEntities)
    }

    @Transaction
    suspend fun insertNewDetail(pokemonEntity: PokemonWithDetailEntity){
        val pokemonStat = pokemonEntity.statEntity
        insertAllStatPokemon(pokemonStat)
        insertAllTypePokemon(pokemonEntity.typeEntity)
        insertAllAbilityPokemon(pokemonEntity.abilityEntity)
        insert(pokemonEntity.pokemonEntity)
    }

    suspend fun updateDetail(pokemonWithDetail: PokemonWithDetailEntity){
        val pokemonEntity = pokemonWithDetail.pokemonEntity
        updateDetailPokemon(pokemonEntity.id, pokemonEntity.idEvolution, pokemonEntity.habitat)
    }

    @Transaction
    suspend fun updateEvolution(pokemonWithDetail: PokemonWithDetailEntity){
        insertAllEvolutionPokemon(pokemonWithDetail.evolutionEntities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStatPokemon(items: PokemonStatEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTypePokemon(items: TypeEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAbilityPokemon(items: AbilityEntities)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvolutionPokemon(items: EvolutionEntities)

    @Query("UPDATE PokemonEntity SET idEvolution = :idEvolution, habitat = :habitat WHERE id = :idPokemon")
    suspend fun updateDetailPokemon(idPokemon : Int, idEvolution: Int, habitat: String)

    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAllPokemon(): PokemonEntities

    @Transaction
    @Query("SELECT * FROM PokemonEntity")
    suspend fun getAllPokemonWithDetail(): PokemonWithStatEntities

    @Transaction
    @Query("SELECT * FROM PokemonEntity WHERE name = :name")
    suspend fun getPokemonWithDetail(name: String): PokemonWithDetailEntity

    @Query("SELECT * FROM PokemonEntity WHERE id= :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): PokemonEntity

    @Query("DELETE FROM PokemonEntity")
    suspend fun deleteAll(): Int
}
