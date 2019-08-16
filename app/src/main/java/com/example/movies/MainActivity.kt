package com.example.movies

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.movies.base.BaseActivity
import com.example.movies.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(), MainNavigator {

    override val vm: MainVM by viewModel()
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.setNav(this)
//        setContentView(R.layout.activity_main)

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://raw.githubusercontent.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//        val apiMovies = retrofit.create(Api::class.java)

//        apiMovies.getMovies()
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ Log.d("RESULT:", "${it.data}") },
//                {
//                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
//                })

        setUpFragments()

    }

    private fun setUpFragments() {
        val fragment = NavHostFragment()
        supportFragmentManager.beginTransaction().replace(R.id.flContent, fragment)
            .setPrimaryNavigationFragment(fragment).commit()
        supportFragmentManager.executePendingTransactions()
        fragment.navController.setGraph(R.navigation.nav_graph_main)
    }

}