package com.sid.roboism.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sid.roboism.data.ProjectsDataModel
import kotlinx.coroutines.launch
import java.io.InputStream

class ProjectsViewModel(private val context: Context) : ViewModel() {
    private val _projectList = mutableListOf<ProjectsDataModel>()
    var error: String? = null

    val projectList: List<ProjectsDataModel> get() = _projectList

    fun getProjectsList() {
        viewModelScope.launch {

            try {
                _projectList.clear()
                val inputStream: InputStream = context.assets.open("projects.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                val project = gson.fromJson(json, Array<ProjectsDataModel>::class.java)
                _projectList.addAll(project)

            } catch (e: Exception) {
                error = e.toString()
                Log.d("Error",error.toString())

            }
        }
    }
}