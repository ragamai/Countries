package com.example.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.Country
import com.example.countries.network.RetrofitAPIService
import com.example.countries.network.RetrofitHelper
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesViewModel: ViewModel() {

    private val retrofit = RetrofitHelper().getRetrofit().create(RetrofitAPIService::class.java)

    //Success livedata
    private var successLiveData = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() {
            return successLiveData
        }

    //Error livedata
    private var errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() {
            return errorLiveData
        }

    //Get list of countries
    fun getCountries() {
        viewModelScope.launch {
            try {
                val countries = retrofit.getCountries()
                if (countries.isSuccessful) {
                    successLiveData.value = countries.body()
                } else {
                    errorLiveData.value = countries.errorBody().toString()
                }
            } catch (e: Exception) {
                errorLiveData.value = e.message.toString()
            }
        }
    }
}