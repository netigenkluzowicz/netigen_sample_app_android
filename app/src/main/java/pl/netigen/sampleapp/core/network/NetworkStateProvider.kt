package pl.netigen.sampleapp.core.network

interface NetworkStateProvider {
    fun isNetworkAvailable(): Boolean
}
