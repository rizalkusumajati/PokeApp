package com.example.pokeapp.data.api.models

import com.example.pokeapp.data.local.entity.*
import com.squareup.moshi.Json

data class PokemonDetailResponse(

	@field:Json(name="location_area_encounters")
	val locationAreaEncounters: String? = null,

	@field:Json(name="types")
	val types: List<TypesItem> = emptyList(),

	@field:Json(name="base_experience")
	val baseExperience: Int? = null,

	@field:Json(name="held_items")
	val heldItems: List<Any?>? = null,

	@field:Json(name="weight")
	val weight: Int? = null,

	@field:Json(name="is_default")
	val isDefault: Boolean? = null,

	@field:Json(name="past_types")
	val pastTypes: List<Any?>? = null,

	@field:Json(name="sprites")
	val sprites: Sprites? = null,

	@field:Json(name="abilities")
	val abilities: List<AbilitiesItem> = emptyList(),

	@field:Json(name="game_indices")
	val gameIndices: List<GameIndicesItem?>? = null,

	@field:Json(name="species")
	val species: Species? = null,

	@field:Json(name="stats")
	val stats: List<StatsItem> = emptyList(),

	@field:Json(name="moves")
	val moves: List<MovesItem?>? = null,

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="id")
	val id: Int? = null,

	@field:Json(name="forms")
	val forms: List<FormsItem?>? = null,

	@field:Json(name="height")
	val height: Int? = null,

	@field:Json(name="order")
	val order: Int? = null
)

fun PokemonDetailResponse.toEntity() =
	PokemonWithDetailEntity(
		PokemonEntity(
			id = id ?: 0,
			name = name ?: "",
			urlImage = sprites?.other?.officialArtwork?.frontDefault ?: "",
			frontDefault = sprites?.frontDefault ?: "",
			frontShinny = sprites?.frontShiny ?: ""
		),
		statEntity = stats.map { it.toEntity(id ?: 0) },
		typeEntity = types.map { it.toEntity(id ?: 0) },
		abilityEntity = abilities.map { it.toEntity(id ?: 0) },
		evolutionEntities = emptyList()
	)


data class Other(

	@field:Json(name="dream_world")
	val dreamWorld: DreamWorld? = null,

	@field:Json(name="official-artwork")
	val officialArtwork: OfficialArtwork? = null
)

data class Crystal(

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class Emerald(

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class DreamWorld(

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null
)

data class FormsItem(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class HeartgoldSoulsilver(

	@field:Json(name="back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:Json(name="back_female")
	val backFemale: Any? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class Animated(

	@field:Json(name="back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:Json(name="back_female")
	val backFemale: Any? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class GenerationIv(

	@field:Json(name="platinum")
	val platinum: Platinum? = null,

	@field:Json(name="diamond-pearl")
	val diamondPearl: DiamondPearl? = null,

	@field:Json(name="heartgold-soulsilver")
	val heartgoldSoulsilver: HeartgoldSoulsilver? = null
)

data class VersionGroup(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class GameIndicesItem(

	@field:Json(name="game_index")
	val gameIndex: Int? = null,

	@field:Json(name="version")
	val version: Version? = null
)

data class GenerationI(

	@field:Json(name="yellow")
	val yellow: Yellow? = null,

	@field:Json(name="red-blue")
	val redBlue: RedBlue? = null
)

data class Stat(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Versions(

	@field:Json(name="generation-iii")
	val generationIii: GenerationIii? = null,

	@field:Json(name="generation-ii")
	val generationIi: GenerationIi? = null,

	@field:Json(name="generation-v")
	val generationV: GenerationV? = null,

	@field:Json(name="generation-iv")
	val generationIv: GenerationIv? = null,

	@field:Json(name="generation-vii")
	val generationVii: GenerationVii? = null,

	@field:Json(name="generation-i")
	val generationI: GenerationI? = null,

	@field:Json(name="generation-viii")
	val generationViii: GenerationViii? = null,

	@field:Json(name="generation-vi")
	val generationVi: GenerationVi? = null
)

data class Icons(

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null
)

data class Gold(

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class GenerationV(

	@field:Json(name="black-white")
	val blackWhite: BlackWhite? = null
)

data class Ability(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class OmegarubyAlphasapphire(

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class Yellow(

	@field:Json(name="front_gray")
	val frontGray: String? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="back_gray")
	val backGray: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null
)

data class UltraSunUltraMoon(

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class BlackWhite(

	@field:Json(name="back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:Json(name="back_female")
	val backFemale: Any? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="animated")
	val animated: Animated? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class OfficialArtwork(

	@field:Json(name="front_default")
	val frontDefault: String? = null
)

data class GenerationVii(

	@field:Json(name="icons")
	val icons: Icons? = null,

	@field:Json(name="ultra-sun-ultra-moon")
	val ultraSunUltraMoon: UltraSunUltraMoon? = null
)

data class XY(

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class GenerationIi(

	@field:Json(name="gold")
	val gold: Gold? = null,

	@field:Json(name="crystal")
	val crystal: Crystal? = null,

	@field:Json(name="silver")
	val silver: Silver? = null
)

data class DiamondPearl(

	@field:Json(name="back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:Json(name="back_female")
	val backFemale: Any? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class GenerationViii(

	@field:Json(name="icons")
	val icons: Icons? = null
)

data class TypesItem(

	@field:Json(name="slot")
	val slot: Int? = null,

	@field:Json(name="type")
	val type: Type? = null
)

fun TypesItem.toEntity(idPokemon: Int) =
	TypeEntity(
		idPokemon = idPokemon,
		idType = type?.name ?: ""
	)

data class FireredLeafgreen(

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class StatsItem(

	@field:Json(name="stat")
	val stat: Stat? = null,

	@field:Json(name="base_stat")
	val baseStat: Int? = null,

	@field:Json(name="effort")
	val effort: Int? = null
)

fun StatsItem.toEntity(idPokemon: Int) =
	PokemonStatEntity(
		baseState = baseStat ?: 0,
		effor = effort ?: 0,
		idStat = stat?.name ?: "",
		idPokemon = idPokemon,
	)

data class Sprites(

	@field:Json(name="back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:Json(name="back_female")
	val backFemale: Any? = null,

	@field:Json(name="other")
	val other: Other? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="versions")
	val versions: Versions? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class MoveLearnMethod(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Silver(

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class GenerationIii(

	@field:Json(name="firered-leafgreen")
	val fireredLeafgreen: FireredLeafgreen? = null,

	@field:Json(name="ruby-sapphire")
	val rubySapphire: RubySapphire? = null,

	@field:Json(name="emerald")
	val emerald: Emerald? = null
)

data class Version(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class Platinum(

	@field:Json(name="back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:Json(name="back_female")
	val backFemale: Any? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="front_female")
	val frontFemale: Any? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class Species(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class VersionGroupDetailsItem(

	@field:Json(name="level_learned_at")
	val levelLearnedAt: Int? = null,

	@field:Json(name="version_group")
	val versionGroup: VersionGroup? = null,

	@field:Json(name="move_learn_method")
	val moveLearnMethod: MoveLearnMethod? = null
)

data class GenerationVi(

	@field:Json(name="omegaruby-alphasapphire")
	val omegarubyAlphasapphire: OmegarubyAlphasapphire? = null,

	@field:Json(name="x-y")
	val xY: XY? = null
)

data class MovesItem(

	@field:Json(name="version_group_details")
	val versionGroupDetails: List<VersionGroupDetailsItem?>? = null,

	@field:Json(name="move")
	val move: Move? = null
)

data class AbilitiesItem(

	@field:Json(name="is_hidden")
	val isHidden: Boolean? = null,

	@field:Json(name="ability")
	val ability: Ability? = null,

	@field:Json(name="slot")
	val slot: Int? = null
)

fun AbilitiesItem.toEntity(idPokemon: Int) =
	AbilityEntity(
		idPokemon = idPokemon,
		idAbility = ability?.name ?: "",
		descAbility = ""
	)

data class RedBlue(

	@field:Json(name="front_gray")
	val frontGray: String? = null,

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="back_gray")
	val backGray: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null
)

data class Move(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)

data class RubySapphire(

	@field:Json(name="back_default")
	val backDefault: String? = null,

	@field:Json(name="front_default")
	val frontDefault: String? = null,

	@field:Json(name="back_shiny")
	val backShiny: String? = null,

	@field:Json(name="front_shiny")
	val frontShiny: String? = null
)

data class Type(

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="url")
	val url: String? = null
)
