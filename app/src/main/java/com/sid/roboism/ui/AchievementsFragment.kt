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
import com.sid.roboism.adapter.AchievementsAdapter
import com.sid.roboism.viewModel.AchievementsViewModel


class AchievementsFragment : Fragment() {
    private lateinit var viewModel: AchievementsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_achievements, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val AchievementsRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view_a)
        AchievementsRecyclerView.layoutManager = LinearLayoutManager(context)
        AchievementsRecyclerView.setHasFixedSize(true)
        viewModel = AchievementsViewModel(requireContext())
        viewModel.getAchievementsList()
//        Log.d("name",viewModel.achivementsList.toString())
        val itemAdapter = AchievementsAdapter(viewModel.achivementsList)
        AchievementsRecyclerView.adapter = itemAdapter

    }

}