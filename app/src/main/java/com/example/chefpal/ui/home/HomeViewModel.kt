package com.example.chefpal.ui.home

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chefpal.Recipe
import com.example.chefpal.databinding.FragmentHomeBinding
import com.example.chefpal.foodLimitationsText
import com.example.chefpal.recipeList
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class HomeViewModel : ViewModel() {
    fun generateRecipes(binding: FragmentHomeBinding, activity: Activity, num:Int) {
        val ingredientsText = binding.ingredientsText.text
        val prompt = if (foodLimitationsText.isNotEmpty())
            "Using the ingredient/s $ingredientsText, create a delicious recipe while making sure to avoid these ingredients: $foodLimitationsText, in the format: name;description;recipe"
        else
            "Using the ingredient/s $ingredientsText, create a delicious recipe in the format: name;description;recipe"

        repeat(num) {
            if (prompt.isNotEmpty()) {
                getResponse(prompt) { response ->
                    activity.runOnUiThread {
                        val splitResponse = response.split(";")
                        recipeList.add(Recipe(splitResponse[0], splitResponse[1], splitResponse[2], false))
                    }
                }
            }
        }
    }

    private val client = OkHttpClient()

    private fun getResponse(question: String, callback: (String) -> Unit) {
        // enter your api key here
        val apiKey=""
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
                val body = response.body?.string()
                if (body != null) {
                    val jsonObject = JSONObject(body)
                    val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                    val textResult = jsonArray.getJSONObject(0).getString("text")
                    callback(textResult)
                } else
                    Log.v("data", "empty")
            }
        })
    }
}

