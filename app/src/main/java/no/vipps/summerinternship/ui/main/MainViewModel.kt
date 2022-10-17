package no.vipps.summerinternship.ui.main

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.vipps.summerinternship.data.api.CountryApi
import no.vipps.summerinternship.config.CountryConfig.DEFAULT_COUNTRY
import no.vipps.summerinternship.data.model.Country
import no.vipps.summerinternship.helper.observable.ObservableList

class MainViewModel(private val app: Application) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel

    private val api = CountryApi(app.baseContext)
    val countries = ObservableList<Country>()
    var searchString = DEFAULT_COUNTRY
        set(value) {
            field = value
            fetchCountries()
        }

    fun initialize() {
        fetchCountries()
    }

    private fun fetchCountries() = this.viewModelScope.launch {
        val updatedCountries = api.fetchCountries(searchString)
        countries.clear()
        countries += updatedCountries
    }


}