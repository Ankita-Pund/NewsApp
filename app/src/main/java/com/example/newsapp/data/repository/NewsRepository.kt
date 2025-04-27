package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NewsApiService

import com.example.newsapp.data.model.NewsResponse
import retrofit2.Response

class NewsRepository(private val apiService: NewsApiService) {
    suspend fun getEverything(query: String, from: String, sortBy: String, apiKey: String): Response<NewsResponse> {
        return apiService.getEverything(query, from, sortBy, apiKey)
    }
}

