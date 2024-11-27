package com.sid.roboism.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.sid.roboism.R
import com.sid.roboism.databinding.FragmentAboutBinding
import com.sid.roboism.databinding.FragmentHomeBinding


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.location.setOnClickListener {
            val url = "geo:0,0?q=23.814681893148613, 86.44121680899379 " +
                    "(Indian Institute of Technology (Indian School of Mines), Dhanbad)"

            val mapUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, mapUri)
            intent.setPackage("com.google.android.apps.maps")
            ContextCompat.startActivity(requireContext(), intent, null)
        }
        binding.facebook.setOnClickListener {
            val url = "https://www.facebook.com/roboism"
            val fbUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW,fbUri)
            ContextCompat.startActivity(requireContext(),intent,null)
        }
        binding.insta.setOnClickListener {
            val url = "https://www.instagram.com/robo__ism"
            val instaUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW,instaUri)
            ContextCompat.startActivity(requireContext(),intent,null)
        }
        binding.linkedin.setOnClickListener {
            val url = "https://www.linkedin.com/company/roboism/"
            val linkedUrl = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW,linkedUrl)
            ContextCompat.startActivity(requireContext(),intent,null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

}

