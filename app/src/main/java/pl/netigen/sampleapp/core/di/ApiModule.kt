package pl.netigen.sampleapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.netigen.sampleapp.core.api.MusicApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideRickAndMortyService(): MusicApi {

        return Retrofit.Builder()
            .baseUrl("https://strapi-drumloops.netigen.eu/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicApi::class.java)
    }
}
