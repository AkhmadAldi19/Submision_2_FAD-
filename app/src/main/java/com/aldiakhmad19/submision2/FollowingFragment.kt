package com.aldiakhmad19.submision2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiakhmad19.submision2.databinding.FollowLayoutBinding

class FollowingFragment : Fragment(R.layout.follow_layout) {

    private var following : FollowLayoutBinding? = null
    private val binding get() = following!!

    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username : String

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailProfileUser.PROFILE_USER).toString()
        following = FollowLayoutBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            recyclerView2.setHasFixedSize(true)
            recyclerView2.layoutManager = LinearLayoutManager(activity)
            recyclerView2.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)
        viewModel.setListFollowers(username)

        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null)
                adapter.setUserList(it)
                showLoading(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        following = null
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar2.visibility = View.VISIBLE
        }else{
            binding.progressBar2.visibility = View.GONE
        }
    }
}