package com.example.movies.ui.movies.details

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.api.entities.Movie
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.ui.MainActivity
import com.example.movies.utils.AppConstans
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment: BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(), MovieDetailsNavigator {

    override val vm: MovieDetailsViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.fragment_movie_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setNav(this)

        setBottomNavigationVisibility(false)

        arguments?.getParcelable<Movie>(AppConstans.ARG_MOVIE_OBJ)?.let { vm.movie = it }

        toast("${vm.movie?.title}")
    }
}