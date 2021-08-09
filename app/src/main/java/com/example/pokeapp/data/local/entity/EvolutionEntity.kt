package com.example.pokeapp.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.pokeapp.domain.models.Evolution

@Entity(primaryKeys = ["idEvolution", "nameEvolution"])
data class EvolutionEntity(
    val idEvolution: Int,
    val nameEvolution: String,
    val minLevel: Int
)

fun EvolutionEntity.toDomain() =
    Evolution(
        nameEvolution,
        minLevel
    )

data class EvolutionWithPokemonEntity(
    @Embedded val evolutionEntity: EvolutionEntity,
    @Relation(
        parentColumn = "nameEvolution",
        entityColumn = "name"
    )
    val pokemonEntity: PokemonEntity
)

fun EvolutionWithPokemonEntity.toDomain() =
    Evolution(
        nameEvolution = evolutionEntity.nameEvolution,
        minLevel = evolutionEntity.minLevel,
        urlImage = pokemonEntity.urlImage
    )
typealias EvolutionEntities = List<EvolutionEntity>
typealias EvolutionWithPokemonEntities = List<EvolutionWithPokemonEntity>

