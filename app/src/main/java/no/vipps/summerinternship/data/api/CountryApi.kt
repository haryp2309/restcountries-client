package no.vipps.summerinternship.data.api

import android.content.Context
import android.widget.Toast
import no.vipps.summerinternship.config.CountryConfig
import no.vipps.summerinternship.data.model.Country
import no.vipps.summerinternship.data.parser.CountryParser
import org.json.JSONObject

class CountryApi(private val context: Context) {
    suspend fun fetchCountries(name: String): Iterable<Country> {

        val url = CountryConfig.API_BASE_URL+"/name/"+name
        val response = VolleyHelper(context).getArray(url)

        return if (response.error != null) {
            Toast.makeText( context, "Fetch failed: ${response.error.networkResponse.statusCode}", Toast.LENGTH_SHORT)
                .show()
            ArrayList()
        } else if (response.data == null) {
            Toast.makeText( context, "Fetch failed: Data is missing", Toast.LENGTH_SHORT)
                .show()
            ArrayList()
        } else CountryParser.multipleFromJson(response.data)

    }
}