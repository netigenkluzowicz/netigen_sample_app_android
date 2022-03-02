package pl.netigen.sampleapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.netigen.sampleapp.features.musiclist.data.local.dao.MusicDao
import pl.netigen.sampleapp.features.musiclist.data.local.model.MusicCached

@Database(entities = [MusicCached::class], version = 1,)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun audioDao(): MusicDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = init(context)
                INSTANCE = instance
                instance
            }
        }

        private fun init(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Database")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val database = getDatabase(context)
                        GlobalScope.launch { database.initDatabase(context) }
                    }
                })
                .build()
        }
    }

    suspend fun initDatabase(context: Context) = Unit
}
