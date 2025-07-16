package com.example.news_app_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app_kotlin.adapter.NewsAdapter
import com.example.news_app_kotlin.databinding.ActivityMainBinding
import com.example.news_app_kotlin.utils.GeminiApiService
import com.example.news_app_kotlin.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            try {
                val summary = withContext(Dispatchers.IO) {
                    GeminiApiService.generateSummary("Summarize: The earth revolves around the sun.")
                }
                Toast.makeText(this@MainActivity, "Gemini Summary: $summary", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e("GeminiApiTest", "API Error", e)
                Toast.makeText(this@MainActivity, "Gemini API Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSearchView()
        observeViewModel()
        setupSwipeRefresh()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            val intent = Intent(this, WebViewActivity::class.java).apply {
                putExtra("url", article.url)
                putExtra("title", article.title)
            }
            startActivity(intent)
        }

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchNews(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.getTopHeadlines()
                }
                return true
            }
        })
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getTopHeadlines()
        }
    }

    private fun observeViewModel() {
        viewModel.articles.observe(this) { articles ->
            newsAdapter.submitList(articles)
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.swipeRefresh.isRefreshing = isLoading
        }

        viewModel.error.observe(this) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
