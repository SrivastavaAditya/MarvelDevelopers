package com.example.adityasrivastava.marvelcharacters.models

/**
 * ResultsItem class
 */
data class ResultsItem(
	val thumbnail: Thumbnail? = null,
	val name: String? = null,
	val description: String? = null,
	val modified: String? = null,
	val id: Int? = null,
	val resourceURI: String? = null
)
