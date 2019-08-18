package com.example.movies.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies.R
import com.example.movies.base.BaseActivity
import com.example.movies.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(), MainNavigator {

    override val vm: MainVM by viewModel()
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.setNav(this)

        setUpNavigation()
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.setupWithNavController(flContent.findNavController())
    }

    private fun setUpNavigation() {
        val fragment = NavHostFragment()
        supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment).setPrimaryNavigationFragment(fragment).commit()
        supportFragmentManager.executePendingTransactions()
        fragment.navController.setGraph(R.navigation.nav_graph_main)
    }

}