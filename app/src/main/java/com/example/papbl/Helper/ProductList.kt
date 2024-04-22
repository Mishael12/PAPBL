package com.example.papbl.Helper

data class ProductList(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
