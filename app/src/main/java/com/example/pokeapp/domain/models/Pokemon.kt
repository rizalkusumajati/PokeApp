package com.example.pokeapp.domain.models


data class Pokemon(
    val id: Int,
    val name: String,
    val urlImage: String = "",
    val frontDefault: String = "",
    val frontShinny: String = "",
    val idEvolution: Int = 0,
    val habitat: String = "",
    val stat: Stats = emptyList(),
    val type: Types = emptyList(),
    val ability: Abilities = emptyList(),
    val evolutions: Evolutions = emptyList()
)

data class Stat(
    val nameStat: String,
    val baseStat: Int
)

data class Type(
    val nameType: String
)

data class Ability(
    val nameAbility: String,
    val descAbility: String
)

data class Evolution(
    val nameEvolution: String,
    val minLevel: Int,
    val urlImage: String = ""
)

data class EvolutionManipulation(
    val nameEvolution1: String,
    val urlEvolution1: String,
    val nameEvolution2: String,
    val urlEvolution2: String,
    val minLevel: Int
)

typealias Pokemons = List<Pokemon>
typealias Stats = List<Stat>
typealias Types = List<Type>
typealias Abilities = List<Ability>
typealias Evolutions = List<Evolution>
