package com.sid.roboism.ui

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sid.roboism.R
import com.sid.roboism.data.CreateAnnouncementModel
import com.sid.roboism.data.PushNotification
import com.sid.roboism.databinding.FragmentNewAnnoucementBinding
import com.sid.roboism.services.FCMService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class NewAnnoucementFragment : Fragment() {

    private val topic = "notification"
    private var _binding: FragmentNewAnnoucementBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewAnnoucementBinding.inflate(inflater, container, false)
        initializeDialog()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            if (binding.etTitle.text.isEmpty()) {
                Toast.makeText(context, "Please enter the title", Toast.LENGTH_SHORT).show()
            } else if (binding.etBody.text.isEmpty()) {
                Toast.makeText(context, "Please enter the body", Toast.LENGTH_SHORT).show()
            } else {
                val title = binding.etTitle.text.toString()
                val body = binding.etBody.text.toString()

                sendAnnouncement(title, body)
            }
        }
    }

    private fun sendAnnouncement(title:String, body:String) {
        dialog.show()
        val fcmService = FCMService(requireContext())  // Pass context here

        // Use lifecycleScope to launch the coroutine
        lifecycleScope.launch {
            fcmService.sendNotification(topic, title, body)
            dialog.dismiss()

        }
    }

    private fun initializeDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.progress_bar)
        dialog.setCancelable(false)
        val layoutParams = WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
        }
        dialog.window?.attributes = layoutParams
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.transparent
                    )
                )
            )
        }
    }
}