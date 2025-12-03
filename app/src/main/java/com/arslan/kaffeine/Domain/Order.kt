package com.arslan.kaffeine.Domain

data class Order(
    val orderId: String = "",
    val items: List<ItemsModel> = listOf(),
    val totalPrice: Double = 0.0,
    val timestamp: Long = 0,
    var status: String = "Processing"
)