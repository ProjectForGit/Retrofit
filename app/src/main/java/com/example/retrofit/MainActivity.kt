package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.Models.UrlImageModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
        sendNetworkRequest()
        }
    }

    fun sendNetworkRequest()
    {
        val builder = Retrofit.Builder().baseUrl("https://aws.random.cat/")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val apiInterface: ApiInterface = retrofit.create<ApiInterface>(ApiInterface::class.java)
        val call: retrofit2.Call<UrlImageModel> = apiInterface.getFile()
        call.enqueue(object : Callback<UrlImageModel> {
            override fun onFailure(call: Call<UrlImageModel>, t: Throwable) {
                Log.i("No", t.message.toString())
            }

            override fun onResponse(call: Call<UrlImageModel>, response: Response<UrlImageModel>) {
                textView.text = response.body()!!.file
            }

        })
    }
}