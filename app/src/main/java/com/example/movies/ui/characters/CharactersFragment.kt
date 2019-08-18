package com.example.movies.ui.characters

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.base.BaseFragment
import com.example.movies.databinding.FragmentCharactersBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment: BaseFragment<FragmentCharactersBinding, CharactersViewModel>(), CharactersNavigator {

    override val vm: CharactersViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.fragment_characters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setNav(this)

        initToolbar()
    }

    private fun initToolbar() {
        hideToolbarLeftIcon()
        setToolbarTitle(getString(R.string.characters))
    }

}