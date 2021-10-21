package com.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.products.R
import com.demo.products.adapter.OrderAdapter
import com.demo.products.datamodel.ProductInfoResponseItem
import kotlinx.android.synthetic.main.orders.*
import java.util.ArrayList

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity
        return inflater.inflate(R.layout.orders,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()

    }
 
    private fun initRecyclerView() {
        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        rv_orders.layoutManager = llm
        val adapter = arguments?.getParcelableArrayList<ProductInfoResponseItem>("list")?.let { OrderAdapter(it) }
        rv_orders.adapter=adapter
    }

    companion object {

        fun newInstance(orderList: ArrayList<ProductInfoResponseItem>?)=
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("list",orderList)
                }
            }
    }

}