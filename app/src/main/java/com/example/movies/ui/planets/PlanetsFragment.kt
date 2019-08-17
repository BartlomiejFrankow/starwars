package com.example.movies.ui.planets

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentPlanetsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PlanetsFragment: BaseFragment<FragmentPlanetsBinding, PlanetsViewModel>(), PlanetsNavigator {

    override val vm: PlanetsViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.fragment_planets

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setNav(this)
    }
}