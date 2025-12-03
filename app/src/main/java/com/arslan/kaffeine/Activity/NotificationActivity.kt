package com.arslan.kaffeine.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.NotificationAdapter
import com.arslan.kaffeine.Domain.Notification
import com.arslan.kaffeine.databinding.ActivityNotificationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.backBtn.setOnClickListener {
            finish()
        }

        loadNotifications()
    }

    private fun loadNotifications() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("Users").document(currentUser.uid).collection("Notifications")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        binding.emptyTxt.visibility = View.VISIBLE
                        binding.notificationView.visibility = View.GONE
                    } else {
                        val notifications = documents.toObjects(Notification::class.java)
                        binding.emptyTxt.visibility = View.GONE
                        binding.notificationView.visibility = View.VISIBLE
                        binding.notificationView.layoutManager = LinearLayoutManager(this)
                        binding.notificationView.adapter = NotificationAdapter(notifications, this)
                    }
                }
        }
    }
}