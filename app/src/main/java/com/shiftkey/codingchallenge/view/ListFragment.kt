package com.shiftkey.codingchallenge.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.adapter.ShiftAdapter
import com.shiftkey.codingchallenge.databinding.FragmentListBinding
import com.shiftkey.codingchallenge.model.ShiftsItem
import com.shiftkey.codingchallenge.network.Repository
import com.shiftkey.codingchallenge.utils.Constants
import com.shiftkey.codingchallenge.viewModel.ShiftViewModel
import com.shiftkey.codingchallenge.viewModel.ShiftViewModelFactory

class ListFragment : Fragment() {

    private var _bind: FragmentListBinding? = null
    private val bind get() = _bind
    private lateinit var shiftViewModel: ShiftViewModel
    private lateinit var shiftAdapter: ShiftAdapter
    private var availableShiftResponse: List<ShiftsItem?> = ArrayList()
    private var shiftsId: ShiftsItem? = null
    var isLoading = false




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lazyLoadShifts()
        _bind = FragmentListBinding.inflate(inflater, container, false)
        shiftAdapter = ShiftAdapter { position ->
            shiftsId = availableShiftResponse[position]
            launchNavigation(shiftsId)

        }

        return bind?.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        bind?.rvMain?.apply {
            val itemDivider = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(itemDivider)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            shiftAdapter.loadShifts(availableShiftResponse as List<ShiftsItem>)
            shiftAdapter.notifyDataSetChanged()
            adapter = shiftAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

//                if (dy > 0) {
                    val visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                    val pastVisibleItem =
                        (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    val total = shiftAdapter.itemCount

                    if (!isLoading) {

                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            isLoading = true
                            shiftViewModel.fetchMoreShift()
                            isLoading = false
                        }

                    }
//                }

                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    private fun lazyLoadShifts() {
        shiftViewModel = ViewModelProvider(
            this,
            ShiftViewModelFactory(Repository.INSTANCE)
        ).get(ShiftViewModel::class.java)
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
            if (it) {
                bind?.progressDialog?.visibility = View.VISIBLE
            } else {
                bind?.progressDialog?.visibility = View.GONE
            }
        })
    }

    private fun launchNavigation(shiftsId: ShiftsItem?) {
        shiftsId?.let {
            val bundle = bundleOf(Constants.SHIFT_KEY to it)
            this.findNavController().navigate(R.id.action_baseFragment_to_detailsFragment, bundle)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }
}