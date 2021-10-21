package com.demo.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.products.R
import com.demo.products.datamodel.ProductInfoResponseItem
import kotlinx.android.synthetic.main.item_user.view.*

class OrderAdapter internal constructor(private val list: List<ProductInfoResponseItem>) : RecyclerView.Adapter<OrderAdapter.UserViewHolder>() {


    var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemLayoutView: View
        context = parent.context
        itemLayoutView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemLayoutView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(holder, position, context)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class UserViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_productname = itemView.tv_productname
        var tv_displayText = itemView.tv_displayText
        var tv_schemeLabelForRetailer = itemView.tv_schemeLabelForRetailer
        var tv_distributorName = itemView.tv_distributorName
        var tv_manufacturerName = itemView.tv_manufacturerName
        var tv_mrp = itemView.tv_mrp
        var tv_ptr = itemView.tv_ptr
        var tv_stock = itemView.tv_stock
        var img_productUrl = itemView.iv_img
        fun bindView(viewHolder: UserViewHolder, position: Int, context: Context?) {
            val item:ProductInfoResponseItem = list.get(position)
           viewHolder.tv_productname.setText(item.productName)
           viewHolder.tv_displayText.setText(item.displayText)
           viewHolder.tv_schemeLabelForRetailer.setText(item.schemeLabelForRetailer)
           viewHolder.tv_distributorName.setText(item.distributorName)
           viewHolder.tv_manufacturerName.setText(item.manufacturerName)
          viewHolder.tv_mrp.setText(context?.getString(R.string.Rs)+ item.mrp.toString())
                 viewHolder.tv_ptr.setText(context?.getString(R.string.Rs)+item.ptr.toString())
               viewHolder.tv_stock.setText("Qty "+item.stock.toString())
                 context?.let { Glide.with(it).load(item.productUrl).into(img_productUrl) };

        }
    }




}