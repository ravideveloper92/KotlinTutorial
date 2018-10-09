package erikjhordanrey.android_kotlin_devises.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.shaadi.R
import com.demo.shaadi.adapter.UserAdapter
import com.demo.shaadi.databinding.ProfilefragBinding
import com.demo.shaadi.viewmodel.UserProfileVM


class UserProfileFragment : Fragment(), UserAdapter.UserRemoveListener, View.OnClickListener {
    override fun onClick(p0: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        binding!!.loading = true
        userProfileVM.getUsers()
    }


    private lateinit var userProfileVM: UserProfileVM
    var binding: ProfilefragBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = ProfilefragBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding!!.loading = true
         binding!!.executePendingBindings()
        initRecyclerView()
    }

    companion object {

        fun newInstance(): UserProfileFragment? {
            return UserProfileFragment()
        }
    }


    private fun initViewModel() {

        userProfileVM = ViewModelProviders.of(this).get(UserProfileVM::class.java)
        userProfileVM.let { lifecycle.addObserver(it) }

    }

    private fun initRecyclerView() {
        binding!!.btnRetry.setOnClickListener(this)
        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding!!.rvUsers.layoutManager = llm
        userProfileVM?.userList?.observe(this, Observer {
            if (it == null) {
                binding!!.loading = null
                binding!!.executePendingBindings()
                binding!!.txtNoResult.text = "No Internet"
                binding!!.ivNoResult.setImageResource(R.drawable.internet)
                binding!!.btnRetry.visibility = View.VISIBLE

            } else {
                binding!!.rvUsers.adapter = UserAdapter(it!!, this)
                binding!!.loading = false
                binding!!.executePendingBindings()

            }

        }
        )
    }

    override fun onUserRemoved(position: Int) {
        binding!!.loading = null
        binding!!.executePendingBindings()
        binding!!.txtNoResult.text = "Not found"
        binding!!.btnRetry.visibility = View.GONE
        binding!!.ivNoResult.setImageResource(R.drawable.notfound)
    }


}
