package com.example.news_app_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.news_app_kotlin.databinding.ActivityWebviewBinding
import com.example.news_app_kotlin.utils.GeminiApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding
    private var articleUrl: String = ""
    private var articleTitle: String = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleUrl = intent.getStringExtra("url") ?: ""
        articleTitle = intent.getStringExtra("title") ?: "Article"

        setupToolbar(articleTitle)
        setupWebView(articleUrl)
        setupOnBackPressed()
    }

    private fun setupToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setTitle(title)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(url: String) {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.progressBar.isVisible = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.isVisible = false
                }
            }

            loadUrl(url)
        }
    }

    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    finish()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_webview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_summary -> {
                showSummaryOptionsDialog()
                true
            }
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSummaryOptionsDialog() {
        val options = arrayOf("Brief Summary", "Detailed Summary", "Bulleted Points")
        var selectedOption = options[0]

        AlertDialog.Builder(this)
            .setTitle("Choose Summary Type")
            .setSingleChoiceItems(options, 0) { _, which ->
                selectedOption = options[which]
            }
            .setPositiveButton("Generate Summary") { _, _ ->
                generateSummary(selectedOption)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun generateSummary(type: String) {
        val prompt = when (type) {
            "Brief Summary" -> "Give a brief summary of the article at $articleUrl"
            "Detailed Summary" -> "Provide a detailed summary of the article at $articleUrl"
            "Bulleted Points" -> "Summarize the article at $articleUrl in bullet points"
            else -> ""
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val summary = GeminiApiService.generateSummary(prompt)
                runOnUiThread { showSummaryResult(summary) }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@WebViewActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showSummaryResult(summary: String) {
        AlertDialog.Builder(this)
            .setTitle("Summary")
            .setMessage(summary)
            .setPositiveButton("OK", null)
            .show()
    }
}
