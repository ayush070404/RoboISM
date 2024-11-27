package com.sid.roboism.services

import android.content.Context
import android.widget.Toast
import com.google.auth.oauth2.GoogleCredentials
import com.sid.roboism.ui.MainActivity
import okhttp3.*

import org.json.JSONObject

import java.io.IOException

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

import java.io.InputStream

class FCMService(private val context: Context) {

    private val client = OkHttpClient()

    private suspend fun getAccessToken(): String = withContext(Dispatchers.IO) {
        val assetManager = context.assets
        val credentialsStream: InputStream =
            assetManager.open("roboism-db0a3-firebase-adminsdk-shmxi-822a7f493c.json")

        val googleCredentials = GoogleCredentials
            .fromStream(credentialsStream)
            .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
        googleCredentials.refreshIfExpired()
        googleCredentials.accessToken.tokenValue
    }

    suspend fun sendNotification(topic: String, title: String, message: String) {
        val accessToken = getAccessToken()
        val json = JSONObject()
        val messageJson = JSONObject()
        val notificationJson = JSONObject()

        notificationJson.put("title", title)
        notificationJson.put("body", message)
        messageJson.put("topic", topic)
        messageJson.put("notification", notificationJson)
        json.put("message", messageJson)

        val body =
            json.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder()
            .url("https://fcm.googleapis.com/v1/projects/roboism-db0a3/messages:send") // Replace YOUR_PROJECT_ID with your Firebase project ID
            .post(body)
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Content-Type", "application/json")
            .build()
        withContext(Dispatchers.IO) {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()

                }

                override fun onResponse(call: Call, response: Response) {
                    println(response.body?.string())

                }
            })
        }
    }
}
