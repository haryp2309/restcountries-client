package no.vipps.summerinternship.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.vipps.summerinternship.R
import no.vipps.summerinternship.data.model.Country
import no.vipps.summerinternship.helper.observable.ObservableListListener
import no.vipps.summerinternship.helper.observable.ObservableList

class CountriesListAdapter(private val context: Context, private val countries: ObservableList<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.CustomViewHolder>() {

    init {
        countries.addListener(ObservableListListener(
            addedListener = { _, index -> this.notifyItemInserted(index) },
            removedListener = { _, index -> this.notifyItemRemoved(index) },
            updatedListener = {_, index -> this.notifyItemChanged(index) },
            clearedListener = { count -> this.notifyItemRangeRemoved(0, count)}
        ))
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryName: TextView = itemView.findViewById(R.id.countryName)
        private val capitalName: TextView = itemView.findViewById(R.id.capitalName)
        private val alternativeSpellings: TextView = itemView.findViewById(R.id.alternativeSpellings)

        fun updateView(country: Country) {
            countryName.text = country.name
            capitalName.text = "Capital: ${country.capital}"
            alternativeSpellings.text = "Alternative Spellings: ${country.altSpellings}"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.updateView(countries[position])


    override fun getItemCount(): Int = countries.size


}