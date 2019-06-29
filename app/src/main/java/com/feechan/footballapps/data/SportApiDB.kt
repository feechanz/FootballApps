package com.feechan.footballapps.data

import com.feechan.footballapps.BuildConfig

object SportApiDB{
    fun getTeams(league: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }

    fun get15EventPast(id: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventspastleague.php?id="+id
    }

    fun get15EventNext(id: String): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventsnextleague.php?id="+id
    }

    fun getEventDetail(eventId: String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupevent.php?id="+eventId
    }

    fun getTeam(teamname: String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchteams.php?t="+teamname
    }

    fun getPlayers(teamname: String) : String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchplayers.php?t="+teamname
    }

    fun getTeamDetail(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupteam.php?id="+teamId
    }

    fun getPlayerDetail(playerid: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupplayer.php?id="+playerid
    }

    fun getSearch(constraint: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchevents.php?e="+constraint
    }
}