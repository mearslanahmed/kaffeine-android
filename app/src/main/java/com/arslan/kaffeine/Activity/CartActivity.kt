package com.arslan.kaffeine.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.CartAdapter
import com.arslan.kaffeine.Domain.Order
import com.arslan.kaffeine.Helper.ChangeNumberItemsListener
import com.arslan.kaffeine.Helper.ManagementCart
import com.arslan.kaffeine.databinding.ActivityCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagementCart
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.apply { 
            listView.layoutManager =
                LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            listView.adapter = CartAdapter(
                managementCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        calculateCart()
                    }
                })
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
        binding.button.setOnClickListener {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val order = Order(
                    orderId = firestore.collection("Users").document().id,
                    items = managementCart.getListCart(),
                    totalPrice = managementCart.getTotalFee() + tax + 15.0,
                    timestamp = System.currentTimeMillis(),
                    status = "Processing"
                )

                firestore.collection("Users").document(currentUser.uid).collection("Orders").add(order)
                    .addOnSuccessListener {
                        managementCart.clearCart()
                        Toast.makeText(this, "Order placed successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MyOrderActivity::class.java))
                        finish()
                    }
            }
        }

        binding.discountBtn.setOnClickListener {
            val discountCode = binding.discountTxt.text.toString()
            if (discountCode == "KAFFEINE10") {
                calculateCart(0.1)
                Toast.makeText(this, "10% discount applied", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid discount code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateCart(discountPercentage: Double = 0.0) {
        val percentTax = 0.02
        val delivery = 15.0
        tax = (managementCart.getTotalFee() * percentTax * 100) / 100.0
        val itemTotal = (managementCart.getTotalFee() * 100) / 100.0

        val totalWithoutDiscount = itemTotal + tax + delivery
        val discountAmount = totalWithoutDiscount * discountPercentage
        val total = (totalWithoutDiscount - discountAmount)

        binding.apply { 
            totalFeeTxt.text = "$${itemTotal}"
            taxFeeTxt.text = "$${tax}"
            deliveryFeeTxt.text = "$${delivery}"
            totalTxt.text = "$${String.format("%.2f", total)}"
        }
    }
}