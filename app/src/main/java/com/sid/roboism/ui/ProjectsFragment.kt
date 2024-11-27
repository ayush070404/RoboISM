package com.sid.roboism.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sid.roboism.R
import com.sid.roboism.adapter.ProjectsAdapter
import com.sid.roboism.viewModel.ProjectsViewModel

class ProjectsFragment : Fragment() {

    private lateinit var viewModel: ProjectsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ProjectsRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view_p)
        ProjectsRecyclerView.layoutManager = LinearLayoutManager(context)
        ProjectsRecyclerView.setHasFixedSize(true)
        viewModel = ProjectsViewModel(requireContext())
        viewModel.getProjectsList()
        Log.d("name",viewModel.projectList.toString())
        val itemAdapter = ProjectsAdapter(requireContext(),viewModel.projectList)
        ProjectsRecyclerView.adapter = itemAdapter

    }

}