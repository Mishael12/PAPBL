package com.example.papbl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papbl.Adapter.RvAdapter
import com.example.papbl.Helper.ProductApi
import com.example.papbl.Helper.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.papbl.databinding.MainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainBinding

    private lateinit var RvAdapter: RvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productApi = RetrofitHelper.getInstance().create(ProductApi::class.java)



        GlobalScope.launch {
            val result = productApi.getProducts()
            if (result != null) {
                Log.d("CHECKAPI", result.body().toString())
                withContext(Dispatchers.Main) {
                    binding.recyclerView.apply {
                        RvAdapter = RvAdapter(result)
                        adapter = RvAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }
        }



    }
}