package no.vipps.summerinternship.data.parser

import no.vipps.summerinternship.data.model.Country
import no.vipps.summerinternship.helper.json.JsonIterable
import org.json.JSONArray
import org.json.JSONObject

object CountryParser {
    fun fromJson(jsonObject: JSONObject) = Country(
        name = jsonObject.getString("name"),
        capital = jsonObject.optString("capital"),
        altSpellings = jsonObject.getJSONArray("altSpellings").length()
    )

    fun multipleFromJson(jsonArray: JSONArray): Iterable<Country> =
        JsonIterable<JSONObject>(jsonArray)
            .map { fromJson(it) }
            .toList()
}