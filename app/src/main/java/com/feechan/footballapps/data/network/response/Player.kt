package com.feechan.footballapps.data.network.response

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strDescriptionEN")
        var playerDescription: String? = null,

        @SerializedName("strPosition")
        var position: String? = null,

        @SerializedName("strCutout")
        var urlProfilePicture: String? = null,

        @SerializedName("strFanart1")
        var urlBannerPicture: String? = null,

        @SerializedName("strHeight")
        var height: String? = null,

        @SerializedName("strWeight")
        var weight: String? = null
)