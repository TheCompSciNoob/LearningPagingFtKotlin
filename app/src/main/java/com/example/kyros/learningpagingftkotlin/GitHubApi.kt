package com.example.kyros.learningpagingftkotlin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("users/{user}/repos")
    fun fetchFeed(@Path("user") user: String,
                  @Query("per_page") resultsPerPage: Int,
                  @Query("page") page: Int): Deferred<Response<List<Repo>>>
}

fun createGitHubApi() = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(GitHubApi::class.java)