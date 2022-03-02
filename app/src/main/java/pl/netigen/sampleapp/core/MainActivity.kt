package pl.netigen.sampleapp.core

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pl.netigen.drumloops.rock.databinding.ActivityMainBinding

import pl.netigen.sampleapp.core.base.BaseActivity
import pl.netigen.sampleapp.core.extension.gone
import pl.netigen.sampleapp.core.extension.visible

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun hideAds() {
        binding.adsBorder.gone()
        binding.adsLayout.gone()
    }

    override fun showAds() {
        binding.adsBorder.visible()
        binding.adsLayout.visible()
    }
}
