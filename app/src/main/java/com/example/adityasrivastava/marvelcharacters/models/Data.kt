package com.example.adityasrivastava.marvelcharacters.models

data class Data(
	val total: Int? = null,
	val offset: Int? = null,
	val limit: Int? = null,
	val count: Int? = null,
	val results: List<ResultsItem?>? = null
)
