package com.example.papbl.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.papbl.Helper.ProductList
import com.example.papbl.databinding.ItemBorderBinding
import com.squareup.picasso.Picasso
import retrofit2.Response


class RvAdapter(private val ProductList: Response<ProductList>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBorderBinding) :
        RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBorderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return ProductList.body()?.products?.size ?:0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val products = ProductList.body()!!.products

        val url = products[position].thumbnail
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(url).into(holder.binding.imgPoster)
        holder.binding.apply {
            txtJudul.text = products[position].title
            txtBrand.text = products[position].brand
            txtDeskripsi.text = products[position].description
            txtCategory.text = products[position].category
            RatingBar.rating = products[position].rating.toFloat()
            Rating.text = products[position].rating.toString()
            btnPrice.text = "$ "+products[position].price.toString()
            Disc.text = "Disc : "+products[position].discountPercentage.toString() +" %"
            Stock.text = products[position].stock.toString() + " left"
        }
    }
}


