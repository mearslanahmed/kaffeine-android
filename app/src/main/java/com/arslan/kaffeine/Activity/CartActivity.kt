package com.arslan.kaffeine.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.CartAdapter
import com.arslan.kaffeine.Helper.ChangeNumberItemsListener
import com.arslan.kaffeine.Helper.ManagmentCart
import com.arslan.kaffeine.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagmentCart
    private var tax: Double = 0.0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        managementCart = ManagmentCart(this)
        
        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        binding.apply { 
            listView.layoutManager =
                LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            listView.adapter = CartAdapter(managementCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener{
                    override fun onChanged(){
                        calculateCart()
                    }
                })
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
        binding.button.setOnClickListener{
            Toast.makeText(this, "Checkout process started!", Toast.LENGTH_SHORT).show()
        }

        binding.discountBtn.setOnClickListener {
            var discountPercentage = binding.discountTxt.text.toString()
            if (discountPercentage == "KAFFEINE10"){
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