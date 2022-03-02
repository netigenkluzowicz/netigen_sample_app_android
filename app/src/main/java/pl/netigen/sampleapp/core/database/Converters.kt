package pl.netigen.sampleapp.core.database

import androidx.room.TypeConverter
import pl.netigen.extensions.fromJson
import pl.netigen.extensions.toJson

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toJson(data: List<String>): String = data.toJson()

        @TypeConverter
        @JvmStatic
        fun fromJson(json: String): List<String> = json.fromJson()
    }
}
