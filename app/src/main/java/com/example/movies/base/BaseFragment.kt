package com.example.movies.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.movies.application.App
import com.example.movies.ui.MainActivity

abstract class BaseFragment<T : ViewDataBinding, out V : BaseViewModel<*>> : Fragment(), BaseNavigator {

    abstract val vm: V
    protected open fun getBindingVariable(): Int = BR.vm

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding: T = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(getBindingVariable(), vm)
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomNavigationVisibility()
    }

    fun setBottomNavigationVisibility(isVisible: Boolean? = true) {
        getParent<MainActivity>()?.vm?.isBottomNavigationVisible?.set(isVisible)
    }

    fun navigate(actionId: NavDirections) {
        NavHostFragment.findNavController(this).navigate(actionId)
    }

    fun navigateById(actionId: Int, arguments: Bundle? = null) {
        NavHostFragment.findNavController(this).navigate(actionId, arguments)
    }

    fun toast(message: String) = Toast.makeText(App.appCtx(), message, Toast.LENGTH_LONG).show()

    inline fun <reified T : BaseActivity<*, *>> getParent(): T? = activity as? T

    override fun showToast(message: String) {
            toast(message)
    }

}