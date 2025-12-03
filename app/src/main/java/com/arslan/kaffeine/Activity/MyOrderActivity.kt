package com.arslan.kaffeine.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.OrderAdapter
import com.arslan.kaffeine.Domain.Order
import com.arslan.kaffeine.databinding.ActivityMyOrderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyOrderBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.backBtn.setOnClickListener { finish() }

        loadOrders()
    }

    private fun loadOrders() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("Users").document(currentUser.uid).collection("Orders")
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        binding.emptyTxt.visibility = View.VISIBLE
                        binding.orderView.visibility = View.GONE
                    } else {
                        val orders = documents.toObjects(Order::class.java)
                        binding.emptyTxt.visibility = View.GONE
                        binding.orderView.visibility = View.VISIBLE
                        binding.orderView.layoutManager = LinearLayoutManager(this)
                        binding.orderView.adapter = OrderAdapter(orders, this)
                    }
                }
        }
    }
}