package pl.netigen.drumloops.rock.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.netigen.drumloops.rock.core.api.AudioApi
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory

import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideRickAndMortyService(): AudioApi = Retrofit.Builder()
            .baseUrl("https://strapi-drumloops.netigen.eu/v2/")
            .addConverterFactory(
                    MoshiConverterFactory.create(
                            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    )
            )
            .build()
            .create(AudioApi::class.java)
}