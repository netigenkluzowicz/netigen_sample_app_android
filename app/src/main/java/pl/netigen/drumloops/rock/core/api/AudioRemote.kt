package pl.netigen.drumloops.rock.core.api
import com.squareup.moshi.Json


data class AudioRemote(
    @Json(name = "baseBpm") val baseBpm: Int,
    @Json(name = "genre") val genre: String,
    @Json(name = "genreColor") val genreColor: String,
    @Json(name = "loopId") val loopId: Int,
    @Json(name = "loopSize") val loopSize: Double,
    @Json(name = "loopUrl") val loopUrl: String,
    @Json(name = "measure") val measure: String,
    @Json(name = "name") val name: String,
    @Json(name = "paymentType") val paymentType: String,
    @Json(name = "serverId") val serverId: Int,
    @Json(name = "tempo") val tempo: String
)