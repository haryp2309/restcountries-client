package no.vipps.summerinternship.data.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class VolleyHelper(private val context: Context) {
    private val queue = Volley.newRequestQueue(context)

    data class Response<T> (
        val data: T?,
        val error: VolleyError?
    )

    suspend fun getObject(url: String) = suspendCoroutine<Response<JSONObject>> { cont ->
        val stringRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { cont.resume(Response(it, null)) },
            { cont.resume(Response(null, it)) }
        )
        queue.add(stringRequest)
    }

    suspend fun getArray(url: String) = suspendCoroutine<Response<JSONArray>> { cont ->
        val stringRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { cont.resume(Response(it, null)) },
            { cont.resume(Response(null, it)) }
        )
        queue.add(stringRequest)
    }
}