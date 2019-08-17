package com.example.movies.ui.movies

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentMoviesBinding
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), MoviesNavigator {

    override val vm: MoviesViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.fragment_movies

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