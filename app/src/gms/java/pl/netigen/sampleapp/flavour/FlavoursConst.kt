package pl.netigen.sampleapp.flavour

import pl.netigen.core.config.AppConfig
import pl.netigen.core.main.CoreMainActivity
import pl.netigen.coreapi.payments.IPayments
import pl.netigen.gms.ads.AdMobAds
import pl.netigen.gms.ads.AdMobAds.Companion.TEST_BANNER_ID
import pl.netigen.gms.ads.AdMobAds.Companion.TEST_INTERSTITIAL_ID
import pl.netigen.gms.ads.AdMobAds.Companion.TEST_REWARDED_ID
import pl.netigen.gms.gdpr.GDPRConsentImpl
import pl.netigen.gms.payments.GMSPayments

object FlavoursConst {
    const val BANNER_AD_ID: String = TEST_BANNER_ID
    const val INTERSTITIAL_AD_ID: String = TEST_INTERSTITIAL_ID
    const val REWARDED_AD_ID: String = TEST_REWARDED_ID

    const val NO_ADS_KEY = ".noads"
    const val SUBSCRIPTION_1 = ".subs_01"

    fun getPaymentsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig): IPayments {
        val inAppSkuList = mutableListOf(
            coreMainActivity.packageName + NO_ADS_KEY,
            coreMainActivity.packageName + SUBSCRIPTION_1,
        )
        val inAppSkuListNoAds = listOf(
            coreMainActivity.packageName + NO_ADS_KEY,
            coreMainActivity.packageName + SUBSCRIPTION_1,
        )
        return GMSPayments(coreMainActivity, inAppSkuList, inAppSkuListNoAds, inAppSkuList, inDebugMode = appConfig.inDebugMode)
    }

    fun getAdsImpl(coreMainActivity: CoreMainActivity, appConfig: AppConfig) = AdMobAds(coreMainActivity, appConfig)
    fun getGDPRConsentImpl(coreMainActivity: CoreMainActivity) = GDPRConsentImpl(coreMainActivity)
}
