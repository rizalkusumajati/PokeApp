package com.example.pokeapp.data.local.entity

import androidx.room.*
import com.example.pokeapp.domain.models.Pokemon

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val urlImage: String = "",
    val frontDefault: String = "",
    val frontShinny: String = "",
    val idEvolution: Int = 0,
    val habitat: String = ""
)

data class PokemonWithDetailEntity(
    @Embedded val pokemonEntity: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPokemon"
    )
    val statEntity: PokemonStatEntities,

    @Relation(
        parentColumn = "id",
        entityColumn = "idPokemon"
    )
    val typeEntity: TypeEntities,

    @Relation(
        parentColumn = "id",
        entityColumn = "idPokemon"
    )
    val abilityEntity: AbilityEntities,

    @Relation(
        parentColumn = "idEvolution",
        entityColumn = "idEvolution"
    )
    val evolutionEntities: EvolutionEntities
)

fun PokemonWithDetailEntity.toDomain() =
    Pokemon(
        id = pokemonEntity.id,
        name = pokemonEntity.name,
        urlImage = pokemonEntity.urlImage,
        frontDefault = pokemonEntity.frontDefault,
        frontShinny = pokemonEntity.frontShinny,
        idEvolution = pokemonEntity.idEvolution,
        habitat = pokemonEntity.habitat,
        stat = statEntity.map { it.toDomain() },
        type = typeEntity.map { it.toDomain() },
        ability = abilityEntity.map { it.toDomain() },
        evolutions = evolutionEntities.map { it.toDomain() }
    )

typealias PokemonWithStatEntities = List<PokemonWithDetailEntity>

fun PokemonEntity.toDomain() =
    Pokemon(
        id = id,
        name = name,
        urlImage = urlImage
    )

typealias PokemonEntities = List<PokemonEntity>
