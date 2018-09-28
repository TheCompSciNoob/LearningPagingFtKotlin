package com.example.kyros.learningpagingftkotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.util.Log
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext

class GitHubDataSource(private val api: GitHubApi, private val user: String):
        PageKeyedDataSource<Int, Repo>(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        launch {
            val initialPage = 1
            val response = api.fetchFeed(user, params.requestedLoadSize, initialPage).await()
            callback.onResult(response.body()!!, null, initialPage + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        launch {
            val response = api.fetchFeed(user, params.requestedLoadSize, params.key).await()
            callback.onResult(response.body()!!, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        //Nothing
    }
}