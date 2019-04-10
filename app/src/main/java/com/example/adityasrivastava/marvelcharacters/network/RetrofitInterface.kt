package com.example.adityasrivastava.marvelcharacters.network

import com.example.adityasrivastava.marvelcharacters.models.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Interface
 */
interface RetrofitInterface {
    @GET("characters")
    fun getMarvelCharacters(@Query(Keys.API_KEY) apiKey: String,
                            @Query(Keys.HASH) hash: String,
                            @Query(Keys.TIME_STAMP) ts: Int,
                            @Query(Keys.OFFSET) offset: Int,
                            @Query(Keys.LIMIT) limit: Int): Observable<Response>
}