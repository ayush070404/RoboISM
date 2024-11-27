package com.sid.roboism.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sid.roboism.data.AchievementsDataModel
import kotlinx.coroutines.launch
import java.io.InputStream


class AchievementsViewModel(private val context: Context) : ViewModel() {
    private val _achivementsList = mutableListOf<AchievementsDataModel>()
    var error: String? = null

    val achivementsList: List<AchievementsDataModel> get() = _achivementsList

    fun getAchievementsList() {
        viewModelScope.launch {

            try {
                _achivementsList.clear()
                val inputStream: InputStream = context.assets.open("achievements.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                val achivements = gson.fromJson(json, Array<AchievementsDataModel>::class.java)
                _achivementsList.addAll(achivements)

            } catch (e: Exception) {
                error = e.toString()
                Log.d("Error",error.toString())

            }
        }
    }
}