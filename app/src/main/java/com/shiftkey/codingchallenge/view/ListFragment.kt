package com.shiftkey.codingchallenge.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.adapter.ShiftAdapter
import com.shiftkey.codingchallenge.databinding.ListFragmentBinding
import com.shiftkey.codingchallenge.model.AvailableShiftResponse
import com.shiftkey.codingchallenge.model.DataItem
import com.shiftkey.codingchallenge.viewModel.ShiftViewModel
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {


    companion object {
        fun newInstance() = ListFragment()
    }

    private var _bind: ListFragmentBinding? = null
    private val bind get() = _bind
    private lateinit var shiftViewModel: ShiftViewModel
    private lateinit var shiftAdapter: ShiftAdapter
    private var availableShiftResponse: List<AvailableShiftResponse> = ArrayList()
    private var shiftsId: DataItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lazyLoadShifts()
        _bind = ListFragmentBinding.inflate(inflater,container,false)
        availableShiftResponse = arguments?.get("shiftList") as List<AvailableShiftResponse>
        shiftAdapter = ShiftAdapter { position ->
            shiftsId = availableShiftResponse[position].data?.get(1)
            launchNavigation()
        }
        return bind?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        rv_main.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            shiftAdapter.loadShifts(availableShiftResponse)
            shiftAdapter.notifyDataSetChanged()
            adapter = shiftAdapter
        }
    }

    private fun lazyLoadShifts() {
        shiftViewModel = ViewModelProvider(this).get(ShiftViewModel::class.java)
        shiftViewModel.showShifts().observe(viewLifecycleOwner, Observer { shiftList ->
            availableShiftResponse = shiftList

        })
        shiftViewModel.fetchShifts()
        shiftViewModel.errorMessage.observe(this, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun launchNavigation() {
        val shiftModel = getShifts()
        val mShifts = mutableListOf<AvailableShiftResponse>()
        mShifts.add(shiftModel)
        val bundle = bundleOf("mShifts" to mShifts)
        this.findNavController().navigate(R.id.action_baseFragment_to_detailsFragment)

    }

    private fun getShifts(): AvailableShiftResponse {
        var dataItem = AvailableShiftResponse()
        availableShiftResponse.forEach { shifts ->
            if (shiftsId == shifts.data?.get(1))
                dataItem = shifts
        }
        return dataItem

    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}