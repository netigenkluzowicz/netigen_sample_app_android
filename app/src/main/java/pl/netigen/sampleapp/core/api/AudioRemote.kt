package pl.netigen.sampleapp.core.api

import com.google.gson.annotations.SerializedName
import pl.netigen.sampleapp.listmusic.domain.model.Audio

data class AudioRemote(
    @SerializedName("baseBpm") val baseBpm: Int,
    @SerializedName("genre") val genre: String,
    @SerializedName("genreColor") val genreColor: String,
    @SerializedName("loopId") val loopId: Int,
    @SerializedName("loopSize") val loopSize: Double,
    @SerializedName("loopUrl") val loopUrl: String,
    @SerializedName("measure") val measure: String,
    @SerializedName("name") val name: String,
    @SerializedName("paymentType") val paymentType: String,
    @SerializedName("serverId") val serverId: Int,
    @SerializedName("tempo") val tempo: String,
) {
    fun toAudio() = Audio(baseBpm, genre, genreColor, loopId, measure, name, paymentType, tempo, isBuy = paymentType.equals("FREE"), isLike = false)
}
