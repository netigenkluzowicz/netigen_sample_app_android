package pl.netigen.sampleapp.core.api

import retrofit2.http.GET

interface MusicApi {
    @GET("loops?apps=1")
    suspend fun getMusics(): MusicResponse
}
