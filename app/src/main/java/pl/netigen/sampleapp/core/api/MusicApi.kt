package pl.netigen.sampleapp.core.api


import pl.netigen.sampleapp.core.base.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface MusicApi {
    @GET("loops?apps=1")
    suspend fun getMusics(): Response<MusicResponse>
}
