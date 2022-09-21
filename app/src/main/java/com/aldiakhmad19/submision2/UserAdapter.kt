package com.aldiakhmad19.submision2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldiakhmad19.submision2.databinding.ListUserBinding
import com.bumptech.glide.Glide

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUserItems = ArrayList<ItemsItem>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itemItem: ItemsItem){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(itemItem)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(itemItem.avatarUrl)
                    .into(ivUser)
                username.text = itemItem.login
                id.text = itemItem.id.toString()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUserItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(users: ArrayList<ItemsItem>) {
        listUserItems.clear()
        listUserItems.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listUserItems.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ItemsItem)
    }

}