package model

import com.google.gson.annotations.SerializedName

data class KWeatherModel(
        @SerializedName("name")
        val cityName: String,
        @SerializedName("main")
        val mainInfo: MainInfo? = null,
        val weather: List<Weather>? = null
)

data class MainInfo(
        val temp: String,
        val humidity: Double
)

data class Weather(
        val icon: String,
        val description: String
)
