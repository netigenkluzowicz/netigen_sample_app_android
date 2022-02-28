package pl.netigen.sampleapp.core.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.main.ICoreViewModelsFactory


abstract class BaseActivity<VB : ViewBinding> : CoreMainActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
    }


    override val viewModelFactory: ICoreViewModelsFactory by lazy { CoreVMFactory(this) }

    abstract fun getViewBinding(): VB
}
