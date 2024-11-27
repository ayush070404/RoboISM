//package com.sid.roboism.retrofit
//
//
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.create
//import java.util.concurrent.TimeUnit
//
//class NotificationRetrofitInstance {
//    companion object {
//        const val FCM_BASE_URL = "https://fcm.googleapis.com/"
//        private val client = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//
//        private val retrofit by lazy {
//            Retrofit.Builder()
//                .baseUrl(FCM_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//
//        val api: NotificationApi by lazy {
//            retrofit.create(NotificationApi::class.java)
//        }
//    }
//}