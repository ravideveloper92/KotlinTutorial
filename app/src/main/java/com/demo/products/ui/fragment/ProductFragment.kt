package erikjhordanrey.android_kotlin_devises.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.products.R
import com.demo.products.viewmodel.ProductVM
import com.onboarding.OrderFragment
import kotlin.collections.ArrayList
import androidx.fragment.app.Fragment
import com.demo.products.databinding.ProductfragBinding
import com.demo.products.datamodel.ProductInfoResponseItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.productfrag.*


class ProductFragment : androidx.fragment.app.Fragment(), View.OnClickListener {

    var tabLayout: TabLayout? = null

    override fun onClick(p0: View?) {
        binding.loading = true
        productVM.callProductDetailsAPI()
    }


    private val productVM: ProductVM by viewModels()
    lateinit var binding: ProductfragBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ProductfragBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.loading = true

        initRecyclerView()
    }

    companion object {

        fun newInstance(): ProductFragment {
            return ProductFragment()
        }
    }


    private fun initViewModel() {
        productVM.let { lifecycle.addObserver(it) }

    }

    private fun initRecyclerView() {
        binding.btnRetry.setOnClickListener(this)
        productVM?.teamsList?.observe(viewLifecycleOwner,  {
            if (it == null) {
                binding.loading = null
                binding.txtNoResult.text = "No Internet"
                binding.ivNoResult.setImageResource(R.drawable.internet)
                binding.btnRetry.visibility = View.VISIBLE
            } else {
                binding.loading = false
                binding.viewPagerSlider.adapter = activity?.let { it1 -> ViewPagerAdapter(it1,it) }

                TabLayoutMediator(tabs, viewPagerSlider) { tab, position ->
                    tab.text =   when(position){
                        0 ->  it.keys.first()
                        1 ->  it.keys.last()
                        else -> it.keys.first()
                    }

                }.attach()
                binding.viewPagerSlider.offscreenPageLimit = 1
            }
        }
        )
    }


    class ViewPagerAdapter(
        fragmentActivity: FragmentActivity,
       val hashMap: HashMap<String, ArrayList<ProductInfoResponseItem>>
    ) :
        FragmentStateAdapter(fragmentActivity) {
        private var mFragmentList: MutableList<androidx.fragment.app.Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()


        override fun getItemCount(): Int {
            return hashMap.size
        }



        override fun createFragment(position: Int): Fragment {
            when(position){
                0 -> return OrderFragment.newInstance(hashMap.get(hashMap.keys.first()))
                1 -> return OrderFragment.newInstance(hashMap.get(hashMap.keys.last()))
                else -> return OrderFragment.newInstance(hashMap.get(hashMap.keys.first()))
            }
        }


        }


}
