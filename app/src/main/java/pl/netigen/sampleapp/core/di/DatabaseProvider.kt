package pl.netigen.sampleapp.core.di

import android.app.Application


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.netigen.sampleapp.core.database.AppDatabase
import pl.netigen.sampleapp.listmusic.data.local.dao.AudioDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseProvider {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return AppDatabase.getDatabase(app)
    }

    @Singleton
    @Provides
    fun audioDao(db: AppDatabase): AudioDao {
        return db.audioDao()
    }

}