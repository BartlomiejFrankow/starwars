package com.example.movies.base

import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.example.movies.application.Actions
import com.example.movies.application.BackPress
import com.example.movies.application.Finish
import com.example.movies.application.Loading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding, out V : BaseVM<*>> : AppCompatActivity(), CoroutineScope, BaseNavigator {

    abstract val vm: V
    protected open fun getBindingVariable(): Int = BR.vm
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        performDataBinding()
    }

    private fun performDataBinding() {
        val viewDataBinding: T = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        lifecycle.addObserver(vm)
        viewDataBinding.setVariable(getBindingVariable(), vm)
        viewDataBinding.executePendingBindings()
    }

    open fun onActions(action: Actions) {
        when (action) {
            is Finish -> finish()
            is BackPress -> onBackPressed().also { onActions(Loading(false)) }
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }

    enum class Animation {
        NONE, SLIDE, TRANSITION, PUSH
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {

        if (currentFocus != null) {
            //  SystemUtilsR.hideSoftInput(this)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun showToast(message: String) {
//        toast(message)
    }
}