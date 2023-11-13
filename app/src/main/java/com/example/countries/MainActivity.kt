package com.example.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.adapter.CountriesAdapter
import com.example.countries.databinding.ActivityMainBinding
import com.example.countries.viewmodel.CountriesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CountriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
        getCountries()
    }

    //call countries api and display the data
    private fun getCountries() {
        showProgress()
        viewModel.getCountries()

        viewModel.countries.observe(this) {
            hideProgress()
            binding.rvCountries.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.rvCountries.layoutManager = LinearLayoutManager(this)
            binding.rvCountries.adapter = CountriesAdapter(it)
        }

        viewModel.error.observe(this) {
            hideProgress()
            binding.rvCountries.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
        }
    }

    //show progress bar
    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    //hide progress bar
    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

}