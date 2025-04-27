package com.example.newsapp.data.api

import com.example.newsapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
@GET("everything")
suspend fun getEverything(
    @Query("q") query: String,
    @Query("from") from: String,
    @Query("sortBy") sortBy: String,
    @Query("apiKey") apiKey: String
): Response<NewsResponse>
}
