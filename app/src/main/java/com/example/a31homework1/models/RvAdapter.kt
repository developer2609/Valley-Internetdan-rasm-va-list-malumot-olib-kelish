package com.example.a31homework1.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a31homework1.databinding.ItemLayoutBinding


class RvAdapter (var list: List<User>) : RecyclerView.Adapter<RvAdapter.VP_Vh>() {

    inner class VP_Vh(var itemListBinding: ItemLayoutBinding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind(user: User) {

            itemListBinding.text1.text=user.login
            itemListBinding.text2.text=user.id.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size



}