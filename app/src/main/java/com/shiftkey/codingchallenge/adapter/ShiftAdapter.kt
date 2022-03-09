package com.shiftkey.codingchallenge.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.ListItemsBinding
import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import com.shiftkey.codingchallenge.model.ShiftsItem
import com.shiftkey.codingchallenge.utils.FormattedDateTimeUtil
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ShiftAdapter(val listener: (Int) -> Unit) :
    RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder>() {

    private var shiftList = mutableListOf<ShiftsItem>()

    class ShiftViewHolder(binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val bind = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftViewHolder =
        ShiftViewHolder(
            ListItemsBinding.inflate(LayoutInflater.from(parent.context))
        )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ShiftViewHolder, position: Int) {
        val shifts = shiftList[position]
        val formattedDateTimeUtil = FormattedDateTimeUtil(shifts)
        holder.bind.shifts = shifts
        holder.bind.dateTime = formattedDateTimeUtil
        holder.bind.rowLayout.setOnClickListener{listener(position)}
    }


    override fun getItemCount(): Int = shiftList.size

    fun loadShifts(shifts: List<ShiftsItem>){
        this.shiftList = shifts.toMutableList()
        notifyDataSetChanged()
    }


}