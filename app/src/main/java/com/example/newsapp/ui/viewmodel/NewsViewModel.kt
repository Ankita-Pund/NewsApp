package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel : ViewModel() {

    val newsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private val repository: NewsRepository

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(com.example.newsapp.data.api.NewsApiService::class.java)
        repository = NewsRepository(api)
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsLiveData.postValue(Resource.Loading())
            try {
                val response = repository.getEverything(query = "tesla",
                    from = "2025-03-27",
                    sortBy = "publishedAt",
                    apiKey = "29846e33a9b941c3bc79e62d4929f30a")
                if (response.isSuccessful) {
                    newsLiveData.postValue(Resource.Success(response.body()!!))
                } else {
                    newsLiveData.postValue(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                newsLiveData.postValue(Resource.Error(e.message ?: "Error Occurred"))
            }
        }
    }
}
