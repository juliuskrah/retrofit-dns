package com.juliuskrah.demo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val list =
        arrayOf("Apple", "Pear", "Mango", "Papaw", "Guava")
    private val random: Random = Random(5)

    private val demoApiServe by lazy {
        ExampleService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.text_view)
        button.setOnClickListener {
            demoApiServe.name().enqueue(object : Callback<Example> {
                override fun onResponse(call: Call<Example>, response: Response<Example>) {
                    if (response.code() == 200) {
                        val texter = response.body()!!.name
                        text.text = texter
                        Toast.makeText(applicationContext, texter, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Example>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed - " + t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}