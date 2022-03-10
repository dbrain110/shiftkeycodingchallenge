package com.shiftkey.codingchallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shiftkey.codingchallenge.databinding.FragmentDetailsBinding
import com.shiftkey.codingchallenge.model.ShiftsItem
import com.shiftkey.codingchallenge.utils.Constants
import com.shiftkey.codingchallenge.utils.FormattedDateTimeUtil

class DetailsFragment : Fragment() {

    private var _bind: FragmentDetailsBinding? = null
    private val bind get() = _bind
    private var shifts: ShiftsItem = ShiftsItem()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentDetailsBinding.inflate(inflater, container,false)
        (arguments?.get(Constants.SHIFT_KEY) as? ShiftsItem)?.let { shifts = it }
        val formattedDateTimeUtil = FormattedDateTimeUtil(shifts)
        bind?.dateTime = formattedDateTimeUtil
        bind?.shiftDetails = shifts
        return bind?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}