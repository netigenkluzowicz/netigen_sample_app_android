package pl.netigen.sampleapp.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.netigen.core.fragment.NetigenVMFragment
import pl.netigen.sampleapp.core.data.ViewEvent
import pl.netigen.sampleapp.core.data.ViewState
import pl.netigen.sampleapp.core.extension.autoCleaned

abstract class BaseFragment<VB : ViewBinding, STATE : ViewState, EVENT : ViewEvent, VM : BaseViewModel<STATE, EVENT>> : NetigenVMFragment() {

    private var _binding: VB by autoCleaned()
    val binding: VB get() = _binding

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun initView()
    abstract fun render(state: STATE)
    abstract fun onEventSent(event: EVENT)

    abstract fun noAdsActive(noAdsActive: Boolean)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeState()
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> render(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        coreMainVM.noAdsActive
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { noAdsActive -> noAdsActive(noAdsActive) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    fun applicationContext(): Context = requireActivity().applicationContext

    fun showToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}
