package com.example.kyros.learningpagingftkotlin

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*class GitHubViewModelFactory(private val user: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GitHubViewModel(user) as T
    }
}*/

class GitHubViewModel: ViewModel() {
    private lateinit var factory: GitHubDataFactory
    lateinit var repos: LiveData<PagedList<Repo>> private set

    init {
        Log.d("GitHubViewModel", "init()")
        loadRepos()
    }

    private fun loadRepos() {
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .build()
        val api = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(GitHubApi::class.java)
        factory = GitHubDataFactory(api, "TheCompSciNoob")
        repos = LivePagedListBuilder(factory, config).build()
    }
}