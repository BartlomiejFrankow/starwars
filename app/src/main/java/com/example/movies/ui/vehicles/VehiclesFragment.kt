package com.example.movies.ui.vehicles

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentVehiclesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class VehiclesFragment: BaseFragment<FragmentVehiclesBinding, VehiclesViewModel>(), VehiclesNavigator {

    override val vm: VehiclesViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.fragment_vehicles

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setNav(this)
    }
}