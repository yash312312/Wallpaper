package com.example.wallpaper.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallpaper.BR
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding: B

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindView(getLayoutId())
    }

    private fun bindView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
        binding.setVariable(BR.viewModel, viewModel)
    }

    abstract fun getViewModelClass(): Class<VM>

    @LayoutRes
    abstract fun getLayoutId(): Int

    fun showToast(messageResId: Int) {
        Toast.makeText(
            this,
            getString(messageResId),
            Toast.LENGTH_SHORT
        ).show()
    }
}