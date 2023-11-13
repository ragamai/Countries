package com.example.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.Country
import com.example.countries.databinding.LayoutListItemBinding

class CountriesAdapter(val countries: List<Country>): RecyclerView.Adapter<CountriesAdapter.CountriesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        val binding = LayoutListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CountriesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) {
        with(holder) {
            with(countries[position]) {
                binding.tvName.text = "$name, $region"
                binding.tvCode.text = "$code"
                binding.tvCapital.text = "$capital"
            }
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountriesListViewHolder(val binding: LayoutListItemBinding)
        :RecyclerView.ViewHolder(binding.root)
}