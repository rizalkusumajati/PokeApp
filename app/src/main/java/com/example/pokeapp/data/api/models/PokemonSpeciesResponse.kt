package com.example.pokeapp.data.api.models

import com.example.pokeapp.data.local.entity.PokemonEntity
import com.example.pokeapp.data.local.entity.PokemonWithDetailEntity
import com.squareup.moshi.Json

data class PokemonSpeciesResponse(

	@field:Json(name="evolution_chain")
	val evolutionChain: EvolutionChain? = null,

	@field:Json(name="genera")
	val genera: List<GeneraItem?>? = null,

	@field:Json(name="habitat")
	val habitat: Habitat? = null,

	@field:Json(name="color")
	val color: Color? = null,

	@field:Json(name="egg_groups")
	val eggGroups: List<EggGroupsItem> = emptyList(),

	@field:Json(name="capture_rate")
	val captureRate: Int? = null,

	@field:Json(name="pokedex_numbers")
	val pokedexNumbers: List<PokedexNumbersItem?>? = null,

	@field:Json(name="forms_switchable")
	val formsSwitchable: Boolean? = null,

	@field:Json(name="growth_rate")
	val growthRate: GrowthRate? = null,

	@field:Json(name="flavor_text_entries")
	val flavorTextEntries: List<FlavorTextEntriesItem?>? = null,

	@field:Json(name="id")
	val id: Int? = null,

	@field:Json(name="is_baby")
	val isBaby: Boolean? = null,

	@field:Json(name="order")
	val order: Int? = null,

	@field:Json(name="generation")
	val generation: Generation? = null,

	@field:Json(name="is_legendary")
	val isLegendary: Boolean? = null,

	@field:Json(name="pal_park_encounters")
	val palParkEncounters: List<PalParkEncountersItem?>? = null,

	@field:Json(name="shape")
	val shape: Shape? = null,

	@field:Json(name="is_mythical")
	val isMythical: Boolean? = null,

	@field:Json(name="base_happiness")
	val baseHappiness: Int? = null,

	@field:Json(name="names")
	val names: List<NamesItem?>? = null,

	@field:Json(name="varieties")
	val varieties: List<VarietiesItem?>? = null,

	@field:Json(name="gender_rate")
	val genderRate: Int? = null,

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="has_gender_differences")
	val hasGenderDifferences: Boolean? = null,

	@field:Json(name="hatch_counter")
	val hatchCounter: Int? = null,

	@field:Json(name="form_descriptions")
	val formDescriptions: List<Any?>? = null,

	@field:Json(name="evolves_from_species")
	val evolvesFromSpecies: EvolvesFromSpecies? = null
)

fun PokemonSpeciesResponse.toEntity() =
	PokemonWithDetailEntity(
		PokemonEntity(
			id = id ?: 0,
			name = name ?: "",
			idEvolution = evolutionChain?.url?.removeSuffix("/")?.split("/")?.last()?.toIntOrNull() ?: 0,
			habitat = habitat?.name ?: ""
		),
		emptyList(),
		emptyList(),
		emptyList(),
		emptyList()
	)


data class Habitat(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class GrowthRate(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class PokedexNumbersItem(

	@field:Json(name="entry_number")
	val entryNumber: Int? = null,

	@field:Json(name="pokedex")
	val pokedex: Pokedex? = null
)

data class VarietiesItem(

	@field:Json(name="pokemon")
	val pokemon: PokemonResult? = null,

	@field:Json(name="is_default")
	val isDefault: Boolean? = null
)

data class Area(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Pokedex(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class PalParkEncountersItem(

	@field:Json(name="area")
	val area: Area? = null,

	@field:Json(name="base_score")
	val baseScore: Int? = null,

	@field:Json(name="rate")
	val rate: Int? = null
)

data class Language(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)


data class PokemonResult(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Shape(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Color(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class EggGroupsItem(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class GeneraItem(

	@field:Json(name="genus")
	val genus: String? = null,

	@field:Json(name="language")
	val language: Language? = null
)

data class FlavorTextEntriesItem(

	@field:Json(name="language")
	val language: Language? = null,

	@field:Json(name="version")
	val version: Version? = null,

	@field:Json(name="flavor_text")
	val flavorText: String? = null
)

data class NamesItem(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="language")
	val language: Language? = null
)

data class EvolvesFromSpecies(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Generation(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class EvolutionChain(

	@field:Json(name="url")
	val url: String? = null
)
