package pl.netigen.sampleapp.core

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import dagger.hilt.android.AndroidEntryPoint
import pl.netigen.drumloops.rock.databinding.ActivityMainBinding
import pl.netigen.sampleapp.core.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun hideAds() {
        binding.adsBorder.visibility = GONE
        binding.adsLayout.visibility = GONE
    }

    override fun showAds() {
        binding.adsBorder.visibility = VISIBLE
        binding.adsLayout.visibility = VISIBLE
    }
}
