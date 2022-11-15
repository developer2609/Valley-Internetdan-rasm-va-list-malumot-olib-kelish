package com.example.a31homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.a31homework1.databinding.ActivityMain2Binding
import com.example.a31homework1.databinding.ActivityMainBinding
import com.example.a31homework1.models.RvAdapter
import com.example.a31homework1.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var list: ArrayList<User>
    lateinit var requestQueue: RequestQueue
    lateinit var rvAdapter: RvAdapter
    var url = "https://api.github.com/users"
    private val TAG = "MainActivity2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        requestQueue = Volley.newRequestQueue(this)
        VolleyLog.DEBUG = true
             doInBackground()
//        val jsonArrayRequest = JsonArrayRequest(
//            Request.Method.GET, url, null,
//            object : Response.Listener<JSONArray> {
//                override fun onResponse(response: JSONArray?) {
//
//                    val type = object : TypeToken<List<User>>(){}.type
//                    val list = Gson().fromJson<List<User>>(response.toString(), type)
//
//                    rvAdapter = RvAdapter(list)
//                     binding.rv.adapter=rvAdapter
//
//                    Log.d(TAG, "onResponse : ${response.toString()}")
//                }
//            }, object : Response.ErrorListener {
//                override fun onErrorResponse(error: VolleyError?) {
//
//                }
//            })
//
//        jsonArrayRequest.tag = "tag1" //tag berilyapti
//        requestQueue.add(jsonArrayRequest)
//
//        requestQueue.cancelAll("tag1") // tag1 dagi so'rovlarni ortga qaytarish uchun



    }

    fun doInBackground(vararg params: Void?): Void? {
                 list= ArrayList()
        val jsonArrayRequest = JsonArrayRequest(GET, url, null,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {

                    list = ArrayList()
                    val type = object : TypeToken<List<User>>() {}.type
                    list = Gson().fromJson<List<User>>(response.toString(), type) as ArrayList<User>
                    rvAdapter = RvAdapter(list)
                    binding.rv.adapter = rvAdapter
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(binding.root.context, "$error", Toast.LENGTH_SHORT).show()
                }
            })

        requestQueue.add(jsonArrayRequest)
        return null
    }

}