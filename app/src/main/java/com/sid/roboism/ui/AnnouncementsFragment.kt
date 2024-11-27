package com.sid.roboism.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sid.roboism.R
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.sid.roboism.data.AnnouncementDataModel
import kotlinx.coroutines.runBlocking

class AnnouncementsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announcements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: AnnouncementDataModel = AnnouncementDataModel("Hii", "Roboism")
        val uri = "mongodb+srv://roboticsclub:NyPHJ6GujnePV2lZ@roboism.rriugap.mongodb.net/" +
                "?retryWrites=true&w=majority&appName=roboism"
        val mongoClient = MongoClient.create(uri)
        val database = mongoClient.getDatabase("roboism")
        val collection = database.getCollection<AnnouncementDataModel>("announcements")
        runBlocking {
            try {
                collection.insertOne(data)
            } catch (e: Exception) {
                Log.d("Mongo", e.toString())

            }
        }


        mongoClient.close()
    }

}