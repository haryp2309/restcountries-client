package no.vipps.summerinternship.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.compose.material.Text
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.vipps.summerinternship.R
import no.vipps.summerinternship.config.CountryConfig.DEFAULT_COUNTRY
import no.vipps.summerinternship.data.model.Country

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchField = view.findViewById<EditText>(R.id.searchField)
        val searchButton = view.findViewById<Button>(R.id.searchButton)
        val countriesList = view.findViewById<RecyclerView>(R.id.countriesList)

        viewModel.initialize()

        searchField.text.insert(0, DEFAULT_COUNTRY)
        searchButton.setOnClickListener {
            viewModel.searchString = searchField.text.toString()
        }

        countriesList.adapter = CountriesListAdapter(view.context, viewModel.countries)
        countriesList.layoutManager = LinearLayoutManager(view.context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}