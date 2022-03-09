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
import com.shiftkey.codingchallenge.model.ShiftsItem
import com.shiftkey.codingchallenge.network.Repository
import com.shiftkey.codingchallenge.viewModel.ShiftViewModel
import com.shiftkey.codingchallenge.viewModel.ShiftViewModelFactory
import kotlinx.android.synthetic.main.list_fragment.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ListFragment : Fragment() {


    companion object {
        fun newInstance() = ListFragment()
    }

    private var _bind: ListFragmentBinding? = null
    private val bind get() = _bind
    private lateinit var shiftViewModel: ShiftViewModel
    private lateinit var shiftAdapter: ShiftAdapter
    private var availableShiftResponse: List<ShiftsItem?> = ArrayList()
    private var shiftsId: ShiftsItem? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lazyLoadShifts()
        _bind = ListFragmentBinding.inflate(inflater,container,false)
//        (arguments?.get("mShift") as? List<AvailableShiftResponse>)?.let { availableShiftResponse = it }
        shiftAdapter = ShiftAdapter { position ->
            shiftsId = availableShiftResponse[position]
            launchNavigation()
        }
        return bind?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        rv_main.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            shiftAdapter.loadShifts(availableShiftResponse as List<ShiftsItem>)
            shiftAdapter.notifyDataSetChanged()
            adapter = shiftAdapter
        }
    }

    private fun lazyLoadShifts() {
        shiftViewModel = ViewModelProvider(this, ShiftViewModelFactory(Repository.INSTANCE)).get(ShiftViewModel::class.java)
        shiftViewModel.showShifts().observe(viewLifecycleOwner, Observer { shiftList ->
            if (shiftList != null) {
                availableShiftResponse = shiftList
            }
            shiftAdapter.loadShifts(shiftList as List<ShiftsItem>)
            shiftAdapter.notifyDataSetChanged()

        })
        shiftViewModel.fetchShifts()
        shiftViewModel.errorMessage.observe(this, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        shiftViewModel.loading.observe(this, Observer {
            if (it){
                bind?.progressDialog?.visibility = View.VISIBLE
            }else{
                bind?.progressDialog?.visibility = View.GONE
            }
        })
    }

    private fun launchNavigation() {
        val shiftModel = getShifts()
        val mShifts = mutableListOf<ShiftsItem>()
        mShifts.add(shiftModel)
        val bundle = bundleOf("mShifts" to mShifts)
        this.findNavController().navigate(R.id.action_baseFragment_to_detailsFragment)

    }
//use for formatting



    private fun getShifts(): ShiftsItem {
        var dataItem = ShiftsItem()
        availableShiftResponse.forEach { shifts ->
            if (shiftsId == shifts)
                dataItem = shifts!!
        }
        return dataItem

    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}