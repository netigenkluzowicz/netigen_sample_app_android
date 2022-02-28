package pl.netigen.sampleapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.netigen.sampleapp.listmusic.data.local.dao.AudioDao
import pl.netigen.sampleapp.listmusic.data.local.model.AudioCached


@Database(entities = [AudioCached::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun audioDao(): AudioDao

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

    suspend fun initDatabase(context: Context) {

    }

}