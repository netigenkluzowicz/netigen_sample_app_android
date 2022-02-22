package pl.netigen.drumloops.rock.core.di

import android.app.Application


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.netigen.drumloops.rock.core.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseProvider {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return AppDatabase.getDatabase(app)
    }


}