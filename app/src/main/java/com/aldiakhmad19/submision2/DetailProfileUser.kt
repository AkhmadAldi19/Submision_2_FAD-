package com.aldiakhmad19.submision2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.aldiakhmad19.submision2.databinding.ActivityDetailProfileUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayoutMediator

class DetailProfileUser : AppCompatActivity() {

    companion object{
        const val PROFILE_USER  = "profile_user"

        @StringRes
        private val TAB_TITTLE = intArrayOf(R.string.followers, R.string.following)
    }

    private lateinit var binding: ActivityDetailProfileUserBinding
    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${binding.username2.text}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        val username = intent.getStringExtra(PROFILE_USER)
        val bundle = Bundle()
        bundle.putString(PROFILE_USER,username)


        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        viewModel.setUserDetail(username)
        viewModel.getUserDetail().observe(this){ detailDataUser ->
            if (detailDataUser!=null){
                binding.apply {
                    name.text = detailDataUser.name
                    username2.text = detailDataUser.login
                    repository.text = detailDataUser.publicRepos.toString()
                    follower.text = detailDataUser.followers.toString()
                    following.text = detailDataUser.following.toString()
                    Location.text = detailDataUser.location
                    Work.text = detailDataUser.company


                    Glide.with(this@DetailProfileUser)
                        .load(detailDataUser.avatarUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .circleCrop()
                        .into(avatar)
                }
            }
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this,bundle)
        binding.apply {
            viewPager2.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabLayout1,viewPager2){tab,position ->
                tab.text = resources.getString(TAB_TITTLE[position])
            }.attach()
        }

    }
}
