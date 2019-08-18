package com.example.movies.ui.movies

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.api.entities.Movie
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.utils.AppConstans
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

    override fun openDetails(movie: Movie) {
        val arguments = Bundle()
        arguments.putParcelable(AppConstans.ARG_MOVIE_OBJ, movie)
        navigateById(R.id.action_moviesFragment_to_movieDetailsFragment, arguments)
    }

}