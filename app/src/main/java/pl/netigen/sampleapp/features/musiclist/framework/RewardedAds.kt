package pl.netigen.sampleapp.features.musiclist.framework

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import pl.netigen.gms.ads.AdMobAds.Companion.TEST_REWARDED_ID
import timber.log.Timber
import javax.inject.Inject

class RewardedAds @Inject constructor() {
    private var mRewardedAd: RewardedAd? = null

    fun initRewardedAds(context: Activity) {
        mRewardedAd?.fullScreenContentCallback = null
        var adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            context, TEST_REWARDED_ID, adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Timber.d("()")
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                }
            }
        )
        initFullScreenContentCallback()
    }

    private fun initFullScreenContentCallback() {
        mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdShowedFullScreenContent() {
                Timber.d("()")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Timber.d("()")
            }

            override fun onAdDismissedFullScreenContent() {
                Timber.d("()")
                mRewardedAd = null
            }
        }
    }

    fun showRewardedAds(context: Activity, addReward: () -> Unit) {
        if (mRewardedAd != null) {
            mRewardedAd?.show(context) {
                fun onUserEarnedReward(rewardItem: RewardItem) {
                    addReward()
                    initRewardedAds(context)
                }
            }
        } else {
            // todo: The rewarded ad wasn't ready yet.
            initRewardedAds(context)
        }
    }
}
