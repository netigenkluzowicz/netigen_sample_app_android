package pl.netigen.sampleapp.core.base

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.core.main.CoreViewModelsFactory
import pl.netigen.coreapi.main.Store
import pl.netigen.drumloops.rock.BuildConfig
import pl.netigen.sampleapp.flavour.FlavoursConst

class CoreVMFactory(override val coreMainActivity: CoreMainActivity) : CoreViewModelsFactory(coreMainActivity) {

    override val appConfig by lazy {
        AppConfig(
            bannerAdId = FlavoursConst.BANNER_AD_ID,
            interstitialAdId = FlavoursConst.INTERSTITIAL_AD_ID,
            rewardedAdId = FlavoursConst.REWARDED_AD_ID,
            inDebugMode = BuildConfig.DEBUG,
            store = when (BuildConfig.FLAVOR) {
                "hms" -> Store.HUAWEI
                "gms" -> Store.GOOGLE_PLAY
                else -> Store.GOOGLE_PLAY
            },
        )
    }

    override val ads = FlavoursConst.getAdsImpl(coreMainActivity, appConfig)

    override val gdprConsent = FlavoursConst.getGDPRConsentImpl(coreMainActivity)

    override val payments = FlavoursConst.getPaymentsImpl(coreMainActivity, appConfig)
}
