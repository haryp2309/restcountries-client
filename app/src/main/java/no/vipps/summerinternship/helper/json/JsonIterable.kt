package no.vipps.summerinternship.helper.json

import org.json.JSONArray

class JsonIterable<T>(private val jsonArray: JSONArray):Iterable<T> {
    override fun iterator(): Iterator<T> = JsonIterator(jsonArray)
}