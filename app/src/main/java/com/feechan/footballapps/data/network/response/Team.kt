package com.feechan.footballapps.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        val id: Long? = null,

        @SerializedName("idTeam")
        var name: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var formYear: String? = null,

        @SerializedName("strStadium")
        var stadium: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null
): Parcelable {
        companion object {
                const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM"
                const val ID: String = "ID_"
                const val TEAM_ID = "idTeam"
                const val TEAM_NAME = "strTeam"
                const val TEAM_BADGE = "strTeamBadge"
                const val FORM_YEAR = "intFormedYear"
                const val STADIUM = "strStadium"
                const val TEAM_DESCRIPTION = "strDescriptionEN"
        }
}