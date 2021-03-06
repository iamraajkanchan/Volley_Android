package com.example.volley_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.MessageQueue
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.ServerError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val SERVER_URL = "https://jsonplaceholder.typicode.com/posts/1"
    }

    private lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestQueue = Volley.newRequestQueue(this)
    }

    override fun onStart() {
        initViews()
        super.onStart()
    }

    private fun initViews() {
        btnShowString.setOnClickListener {
            val stringRequest =
                StringRequest(Request.Method.GET, SERVER_URL, { response ->
                    txtLabel.text = response.substring(0, 50)
                }, { error ->
                    txtLabel.text = error.toString()
                })
            requestQueue.add(stringRequest)
        }

        /* As it is a GET Request, you can pass null for JSON Request*/
        btnShowJSON.setOnClickListener {
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, SERVER_URL, null, {
                txtLabel.text = it.toString().substring(0, 50)
            }, {
                txtLabel.text = it.stackTraceToString()
            })
            requestQueue.add(jsonObjectRequest)
        }
    }
}