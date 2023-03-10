package com.ezatpanah.databinding_paging_youtube.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.databinding_paging_youtube.adapter.LoadMoreAdapter
import com.ezatpanah.databinding_paging_youtube.adapter.NewsAdapter
import com.ezatpanah.databinding_paging_youtube.databinding.ActivityMainBinding
import com.ezatpanah.databinding_paging_youtube.utils.initRecycler
import com.ezatpanah.databinding_paging_youtube.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            lifecycleScope.launchWhenCreated {
                viewModel.newsList.collect {
                    newsAdapter.submitData(it)
                }
            }

            newsAdapter.setOnItemClickListener {
                val uri = Uri.parse(it.url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                this@MainActivity.startActivity(intent)
            }

            lifecycleScope.launchWhenCreated {
                newsAdapter.loadStateFlow.collect {
                    val state = it.refresh
                    prgBarMovies.isVisible = state is LoadState.Loading
                }
            }

            rvLastNews.apply {

                initRecycler(LinearLayoutManager(this@MainActivity), newsAdapter)

                adapter = newsAdapter.withLoadStateFooter(
                    LoadMoreAdapter {
                        newsAdapter.retry()
                    }
                )
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}