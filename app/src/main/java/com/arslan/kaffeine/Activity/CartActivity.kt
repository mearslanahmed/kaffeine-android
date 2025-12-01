package com.arslan.kaffeine.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arslan.kaffeine.Adapter.CartAdapter
import com.arslan.kaffeine.Helper.ChangeNumberItemsListener
import com.arslan.kaffeine.Helper.ManagementCart
import com.arslan.kaffeine.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagementCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

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
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15
        tax = ((managementCart.getTotalFee()*percentTax)*100)/100.0
        val total = ((managementCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = (managementCart.getTotalFee()*100)/100
        binding.apply {
            totalFeeTxt.text = "$$itemTotal"
            taxFeeTxt.text = "$$tax"
            deliveryFeeTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }

    }
}