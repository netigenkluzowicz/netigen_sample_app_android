package pl.netigen.drumloops.rock.core

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pl.netigen.drumloops.rock.core.base.BaseActivity
import pl.netigen.drumloops.rock.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}