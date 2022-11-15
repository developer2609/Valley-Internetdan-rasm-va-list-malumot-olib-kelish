package com.example.a31homework1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Request.Method.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.a31homework1.databinding.ActivityMain2Binding
import com.example.a31homework1.databinding.ActivityMainBinding
import com.example.a31homework1.models.NetworkHelper
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var networkHelper: NetworkHelper
    lateinit var requestQueue: RequestQueue

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        networkHelper = NetworkHelper(this)

        if (networkHelper.isNetworkConnected()) {
            binding.textView.text = "Connected"

            requestQueue = Volley.newRequestQueue(this)

                binding.button1.setOnClickListener {
                    featchObjectLoad()
                    fetchImageLoad()


                }

            binding.button2.setOnClickListener {

           intent=Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }



        } else {
            binding.textView.text = "Disconnected"
        }
    }

    private fun featchObjectLoad() {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "http://ip.jsontest.com", null,
            object: Response.Listener<JSONObject>{//xatolik
            override fun onResponse(response: JSONObject?) {
                val strstring = response?.getString("ip")
                binding.textView.text = strstring
            }
            }, object : Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {
                    binding.textView.text = error?.message
                }
            })
        requestQueue.add(jsonObjectRequest)
    }


//        val jsonObjectRequest =
//            JsonObjectRequest(Request.Method.GET, "http://ip.jsontest.com", null,
//                object : Response.Listener<JSONObject> {
//                    override fun onResponse(response: JSONObject?) {
//                        val strstring = response?.getString("ip")
//                        binding.ipText.text = strstring
//                    }
//                }, object : Response.ErrorListener {
//                    override fun onErrorResponse(error: VolleyError?) {
//                        binding.textView.text = error?.message
//                    }
//                })
//        requestQueue.add(jsonObjectRequest)


    private fun fetchImageLoad() {
        val imageRequest = ImageRequest("https://i.imgur.com/Nwk25LA.jpg",
            object : Response.Listener<Bitmap> {
                override fun onResponse(response: Bitmap?) {
                    binding.image.setImageBitmap(response)
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    binding.textView.text = error?.message
                }
            })

        requestQueue.add(imageRequest)
    }



}