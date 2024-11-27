package com.sid.roboism.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessaging
import com.sid.roboism.R
import com.sid.roboism.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        askNotificationPermission()
        binding.btnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        navController = findNavController(R.id.nav_host_fragment)

        binding.navView.setupWithNavController(navController)
        binding.navView.setCheckedItem(R.id.IOTFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment -> binding.navView.setCheckedItem(R.id.homeFragment)
                R.id.IOTFragment -> binding.navView.setCheckedItem(R.id.IOTFragment)
                R.id.projectsFragment -> binding.navView.setCheckedItem(R.id.projectsFragment)
                R.id.achievementsFragment -> binding.navView.setCheckedItem(R.id.achievementsFragment)
                R.id.announcementsFragment -> binding.navView.setCheckedItem(R.id.announcementsFragment)
                R.id.newAnnoucementFragment -> binding.navView.setCheckedItem(R.id.newAnnoucementFragment)
                R.id.aboutFragment -> binding.navView.setCheckedItem(R.id.aboutFragment)
                R.id.ourTeamFragment -> binding.navView.setCheckedItem(R.id.ourTeamFragment)

                else -> binding.navView.setCheckedItem(R.id.homeFragment)
            }


            binding.tvTittle.text = when (destination.id) {
                R.id.homeFragment -> "Home"
                R.id.IOTFragment -> "IOT"
                R.id.projectsFragment -> "Projects"
                R.id.achievementsFragment -> "Achievements"
                R.id.announcementsFragment -> "Announcements"
                R.id.newAnnoucementFragment -> "New Announcement"
                R.id.aboutFragment -> "About Us"
                R.id.ourTeamFragment -> "Our Team"
                else -> "Home"
            }

        }

        FirebaseMessaging.getInstance().subscribeToTopic("notification")
            .addOnCompleteListener { task ->
                var msg = "Subscribed to topic"
                if (!task.isSuccessful) {
                    msg = "Subscription to topic failed"
                }
                Log.d("MainActivity", msg)
            }
    }
    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS

            if (ContextCompat.checkSelfPermission(
                    this,
                    notificationPermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(notificationPermission)
            }
        }
    }

}