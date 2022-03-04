package pl.netigen.sampleapp.features.musiclist.framework

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class RewardedAds @Inject constructor(@ActivityContext private val activity: Context) {
    private var mRewardedAd: RewardedAd? = null

    fun initRewardedAds() {
        mRewardedAd?.fullScreenContentCallback = null
        var adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            activity, "ca-app-pub-4699516034931013/6159464536", adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    Timber.d(error.toString())
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                }
            },
        )

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
                initRewardedAds()
            }
        }
    }

    fun showRewardedAds(addReward: () -> Unit) {
        if (mRewardedAd != null) {
            mRewardedAd?.show(
                activity as Activity,
            ) {
                addReward()
            }
        } else {
            // todo: The rewarded ad wasn't ready yet.
            initRewardedAds()
        }
    }
}
