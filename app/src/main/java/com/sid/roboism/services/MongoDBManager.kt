package com.sid.roboism.services

import org.bson.Document
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.runBlocking


object MongoClientConnectionExample {

    fun main() {
        // Replace the placeholders with your credentials and hostname
        val connectionString = "mongodb+srv://roboticsclub:NyPHJ6GujnePV2lZ@roboism.mm3wlzz.mongodb.net/?retryWrites=true&w=majority&appName=roboism"

        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()

        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(connectionString))
            .serverApi(serverApi)
            .build()

        // Create a new client and connect to the server
        MongoClient.create(mongoClientSettings).use { mongoClient ->
            val database = mongoClient.getDatabase("roboism")
            runBlocking {
                database.runCommand(Document("ping", 1))
            }
            println("Pinged your deployment. You successfully connected to MongoDB!")
        }
    }

}
