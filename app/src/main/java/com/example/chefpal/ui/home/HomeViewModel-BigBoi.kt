package com.example.chefpal.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chefpal.databinding.FragmentHomeBinding
import com.example.chefpal.foodLimitationsText
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class HomeViewModel : ViewModel() {
    fun generateRecipes(binding: FragmentHomeBinding, num:Int) {
        val ingredientsText = binding.ingredientsText.text
        val prompt = if (foodLimitationsText.isNotEmpty())
            "Using the ingredient/s $ingredientsText, create a delicious recipe while making sure to avoid these ingredients: $foodLimitationsText"
        else
            "Using the ingredient/s $ingredientsText, create a delicious recipe"

        repeat(num) {
            getResponse(prompt)
        }
    }

    private val client = OkHttpClient()

    private fun getResponse(question: String) {
        val apiKey="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"
        val url="https://api.openai.com/v1/completions"

        val requestBody="""
            {
            "model": "gpt-3.5-turbo-instruct",
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "API failed", e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body=response.body?.string()
                if (body != null)
                    Log.v("data", body)
                else
                    Log.v("data", "empty")
            }
        })
    }
}

