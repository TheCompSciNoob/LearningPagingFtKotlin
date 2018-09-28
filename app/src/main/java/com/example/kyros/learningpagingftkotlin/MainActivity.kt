package com.example.kyros.learningpagingftkotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDataFromGitHub()
    }

    private fun loadDataFromGitHub() {
        val viewModel = ViewModelProviders.of(this).get(GitHubViewModel::class.java)
        Log.d("MainActivity", "ViewModel initialized.")
        Log.d("MainActivity", "Repos: ${viewModel.repos.value}")
    }
}
