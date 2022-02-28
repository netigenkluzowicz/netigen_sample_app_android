package pl.netigen.sampleapp.core.api


import retrofit2.http.GET


interface AudioApi {
    @GET("loops?apps=1")
    suspend fun getMusics(): AudioResponse
}