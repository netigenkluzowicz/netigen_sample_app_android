package pl.netigen.sampleapp.features.musiclist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.netigen.drumloops.rock.databinding.ListMusicFragmentBinding
import pl.netigen.sampleapp.core.base.BaseFragment
import pl.netigen.sampleapp.core.extension.autoCleaned
import pl.netigen.sampleapp.core.extension.gone
import pl.netigen.sampleapp.core.extension.visible
import pl.netigen.sampleapp.features.musiclist.framework.RewardedAds
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicDisplayable
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicListContract
import pl.netigen.sampleapp.flavour.FlavoursConst.NO_ADS_KEY
import pl.netigen.sampleapp.flavour.FlavoursConst.SUBSCRIPTION_1
import javax.inject.Inject

@AndroidEntryPoint
class ListMusicFragment :
    BaseFragment<ListMusicFragmentBinding, MusicListContract.MusicListState, MusicListContract.MusicListEvent, ListMusicViewModel>() {

    @Inject
    lateinit var rewardedAd: RewardedAds

    override val viewModel: ListMusicViewModel by viewModels()

    private val audioListAdapter by autoCleaned(initializer = { ListMusicAdapter(::onMusicClicked, ::onLikeMusicClick) })
    private val likeListAdapter by autoCleaned(initializer = { ListMusicAdapter(::onMusicClicked, ::onLikeMusicClick) })

    override fun initView() {
        binding.run {
            likeMusicRecyclerView.adapter = likeListAdapter
            likeMusicRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            allMusicRecyclerView.adapter = audioListAdapter
            allMusicRecyclerView.layoutManager = GridLayoutManager(context, 3)

            buyPremiumButton.setOnClickListener { buy(NO_ADS_KEY) }
            buySubscribeButton.setOnClickListener { buy(SUBSCRIPTION_1) }
        }
    }

    private fun buy(nameBuyObject: String) {
        activity?.let {
            coreMainVM.makeNoAdsPayment(it, coreMainVM.packageName + nameBuyObject)
        }
    }

    private fun onMusicClicked(musicDisplayable: MusicDisplayable) {
        if (musicDisplayable.isBuy.not()) {
            activity?.let { rewardedAd.showRewardedAds { rewardedMusic(musicDisplayable) } }
        }
    }

    private fun rewardedMusic(music: MusicDisplayable) = onEventSent(MusicListContract.MusicListEvent.BuyMusicForRewardedAd(music))

    private fun onLikeMusicClick(musicDisplayable: MusicDisplayable) =
        onEventSent(MusicListContract.MusicListEvent.LikeMusicClicked(musicDisplayable))

    override fun render(state: MusicListContract.MusicListState) {
        val error = state.error
        if (error != "") {
            showToast(error)
        }

        if (state.isLoading) binding.progressBar.visible() else binding.progressBar.gone()

        val allMusic = state.allMusic
        if (allMusic.isNotEmpty()) {
            allMusicLoaded(allMusic)
        }

        if (state.isUserPremium) {
            binding.run {
                buyPremiumButton.gone()
                buySubscribeButton.gone()
            }
        } else {
            binding.run {
                buyPremiumButton.visible()
                buySubscribeButton.visible()
            }
        }

        val likeMusic = state.likeMusic
        likeMusicLoaded(likeMusic)
    }

    override fun noAdsActive(noAdsActive: Boolean) = onEventSent(MusicListContract.MusicListEvent.SetNoAdsActive(noAdsActive))

    private fun likeMusicLoaded(musicList: List<MusicDisplayable>) = likeListAdapter.submitList(musicList)

    private fun allMusicLoaded(musicList: List<MusicDisplayable>) = audioListAdapter.submitList(musicList)

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) = ListMusicFragmentBinding.inflate(inflater, container, false)

    override fun onEventSent(event: MusicListContract.MusicListEvent) = viewModel.setEvent(event)
}
