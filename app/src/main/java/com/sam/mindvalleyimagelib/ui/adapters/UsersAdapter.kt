package com.sam.mindvalleyimagelib.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam.mindvalleyimagelib.R
import com.sam.mindvalleyimagelib.models.ImagesResponseModel
import com.sam.mindvalleyimagelib.utils.AppUtils
import kotlinx.android.synthetic.main.user_item.view.*

class UsersAdapter(var list: ArrayList<ImagesResponseModel>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_item,
                parent,
                false
            ) as View
        return ViewHolder(view)
    }

    fun updateList(newList: List<ImagesResponseModel>) {
        if (list.size > 100) {
            newList.forEach {
                list.removeAt(0)
                list.add(list.size - 1, it)
            }
        } else
            list.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list.get(position)

        AppUtils.setImageFromUri(user.urls?.small!!, holder.ivProfilePic)
        holder.tv_name.text = user.user?.username
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProfilePic = itemView.iv_pic
        var tv_name = itemView.tv_name


    }

}