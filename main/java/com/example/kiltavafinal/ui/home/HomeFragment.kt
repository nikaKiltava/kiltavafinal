package com.example.kiltavafinal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kiltavafinal.databinding.FragmentHomeBinding
import com.example.kiltavafinal.ui.adapter.MovieAdapter
import com.example.kiltavafinal.data.api.RetrofitInstance
import com.example.kiltavafinal.databinding.FragmentHomeBinding
import com.example.kiltavafinal.ui.adapter.MovieAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
        get() = field
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        fetchMovies()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter { movie ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = movieAdapter
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            val response = RetrofitInstance.api.getPopularMovies("e6e06c56ea7588cf19a39b8588c38850")
            movieAdapter.submitList(response.results)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}