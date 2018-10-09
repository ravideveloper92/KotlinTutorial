package com.demo.shaadi.adapter

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.demo.shaadi.R
import com.demo.shaadi.datamodel.ResultsItem
import com.demo.shaadi.util.AnimUtil
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter internal constructor(private val list: List<ResultsItem>, private val userRemoveListener: UserRemoveListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var myList: MutableList<ResultsItem> = list as MutableList<ResultsItem>

    private var lastPosition = -1
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
        holder.itemView.setOnClickListener { v ->
            AnimUtil.animateItemRemove(context, holder.itemView);
            Handler().postDelayed({
                if (myList.size > 1) {
                    myList.removeAt(position)
                } else {
                    userRemoveListener.onUserRemoved(position)
                }
                notifyDataSetChanged()


            }, 300)
        }
    }


    override fun getItemCount(): Int {
        return myList.size
    }

    inner class UserViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivUser = itemView.iv_user
        var tvName = itemView.tv_name
        var tvAgeHeight = itemView.tv_age_height
        var tvCityState = itemView.tv_city_state
        var tvRemove = itemView.tv_remove
        var cvRoot = itemView.cv_root


        fun bindView(viewHolder: UserViewHolder, position: Int, context: Context?) {
            val item = myList.get(position)
            viewHolder.tvName?.text = item.name?.first + " " + item.name?.last
            viewHolder.tvAgeHeight?.text = item.dob?.age + " yrs"
            viewHolder.tvCityState?.text = item.location?.city + ", " + item.location?.state
            if (context != null) {
                Glide.with(context)
                        .load(item.picture?.large)
                        .into(ivUser)

            }
        }


    }



    interface UserRemoveListener {
        fun onUserRemoved(position: Int)
    }
}