package com.shiftkey.codingchallenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.ListItemsBinding
import com.shiftkey.codingchallenge.model.AvailableShiftResponse

class ShiftAdapter(val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder>() {

    private var shiftList = mutableListOf<AvailableShiftResponse>()

    class ShiftViewHolder(binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val bind = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftViewHolder =
        ShiftViewHolder(
            ListItemsBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: ShiftViewHolder, position: Int) {
        val shifts = shiftList[position]
        holder.bind.shiftModel = shifts
        holder.bind.rowLayout.setOnClickListener{listener(position)}
    }

    override fun getItemCount(): Int = shiftList.size

    fun loadShifts(shifts: List<AvailableShiftResponse>){
        this.shiftList = shifts.toMutableList()
        notifyDataSetChanged()
    }


}