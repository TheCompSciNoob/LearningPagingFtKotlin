package com.example.kyros.learningpagingftkotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.cancel

class GitHubDataFactory(private val api: GitHubApi, private val user: String): DataSource.Factory<Int, Repo>() {
    private lateinit var dataSource: GitHubDataSource

    override fun create(): DataSource<Int, Repo> {
        dataSource = GitHubDataSource(api, user)
        return dataSource
    }

    fun stop() = dataSource.coroutineContext.cancel()
}