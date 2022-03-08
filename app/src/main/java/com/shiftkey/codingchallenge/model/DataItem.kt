package com.shiftkey.codingchallenge.model

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("shifts")
    val shifts: List<ShiftsItem?>? = null
)