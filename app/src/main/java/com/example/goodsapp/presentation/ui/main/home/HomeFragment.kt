package com.example.goodsapp.presentation.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.goodsapp.databinding.FragmentHomeBinding
import com.example.goodsapp.domain.model.AssetLocation
import com.example.goodsapp.domain.model.AssetStatus
import com.example.goodsapp.presentation.state.MainUiState
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is MainUiState.Initial -> {  }
                        is MainUiState.Loading -> showLoading(true)
                        is MainUiState.Success -> {
                            showLoading(false)
                            updateStatusSection(state.assetStatus)
                            updateLocationSection(state.assetLocation)
                        }
                        is MainUiState.Error -> {
                            showLoading(false)
                            showError(state.message)
                        }
                    }
                }
            }
        }
    }

    private fun updateStatusSection(statusList: List<AssetStatus>) {
        // Update status cards
        statusList.forEach { status ->
            when (status.status.name.lowercase()) {
                "sold" -> binding.cardSold.setData("Asset Sold", status.count)
                "in stock" -> binding.cardStock.setData("Asset in Stock", status.count)
                "expired" -> binding.cardExpired.setData("Expired Asset", status.count)
            }
        }

        // Update status chart
        val entries = statusList.mapIndexed { index, status ->
            BarEntry(index.toFloat(), status.count.toFloat())
        }
        binding.chartStatus.setData(
            title = "Status Chart",
            data = entries,
            labels = statusList.map { it.status.name }
        )
    }

    private fun updateLocationSection(locationList: List<AssetLocation>) {
        // Update location cards
        locationList.forEach { location ->
            when (location.location.name.lowercase()) {
                "warehouse" -> binding.cardWarehouse.setData("Warehouse", location.count)
                "sales rack" -> binding.cardSalesRack.setData("Sales Rack", location.count)
            }
        }

        // Update location chart
        val entries = locationList.mapIndexed { index, location ->
            BarEntry(index.toFloat(), location.count.toFloat())
        }
        binding.chartLocation.setData(
            title = "Location Chart",
            data = entries,
            labels = locationList.map { it.location.name }
        )
    }

    private fun showLoading(isLoading: Boolean) {
        // Implement loading state
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}