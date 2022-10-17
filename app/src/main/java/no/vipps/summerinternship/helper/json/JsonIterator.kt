package no.vipps.summerinternship.helper.json

import org.json.JSONArray

class JsonIterator<T>(private val jsonArray: JSONArray): Iterator<T> {
    var index = 0

    override fun hasNext(): Boolean = index < jsonArray.length()
    override fun next(): T = jsonArray.get(index++) as T

}