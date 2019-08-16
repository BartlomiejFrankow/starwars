package com.example.movies.home

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeVM>(), HomeNavigator {

    override val vm: HomeVM by viewModel()
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setNav(this)

        vm.getMovies()

        rvMoviesIni()
    }

    private fun rvMoviesIni() {
        rvMovies.adapter = vm.adapter
    }

}