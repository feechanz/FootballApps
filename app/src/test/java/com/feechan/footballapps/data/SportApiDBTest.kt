package com.feechan.footballapps.data

import com.feechan.footballapps.BuildConfig
import junit.framework.Assert
import org.junit.Test

class SportApiDBTest {
    @Test
    fun testGetTeams(){
        val league = "league"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/search_all_teams.php?l=${league}"
        Assert.assertEquals(url, SportApiDB.getTeams(league))
    }

    @Test
    fun testGet15EventPast(){
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventspastleague.php?id=${id}"
        Assert.assertEquals(url, SportApiDB.get15EventPast(id))
    }

    @Test
    fun testGet15EventNext(){
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/eventsnextleague.php?id=${id}"
        Assert.assertEquals(url, SportApiDB.get15EventNext(id))
    }

    @Test
    fun testGetEventDetail() {
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupevent.php?id=${id}"
        Assert.assertEquals(url, SportApiDB.getEventDetail(id))
    }

    @Test
    fun testGetTeam(){
        val teamname = "teamname"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchteams.php?t=${teamname}"
        Assert.assertEquals(url, SportApiDB.getTeam(teamname))
    }

    @Test
    fun getPlayers(){
        val teamname = "teamname"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchplayers.php?t="+teamname
        Assert.assertEquals(url, SportApiDB.getPlayers(teamname))
    }

    @Test
    fun getTeamDetail() {
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/" + BuildConfig.TSDB_API_KEY + "/lookupteam.php?id=" + id
        Assert.assertEquals(url, SportApiDB.getTeamDetail(id))
    }

    @Test
    fun getPlayerDetail(){
        val id = "123"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/lookupplayer.php?id="+id
        Assert.assertEquals(url, SportApiDB.getPlayerDetail(id))
    }

    @Test
    fun getSearch(){
        val constraint = "AAA"
        val url = BuildConfig.BASE_URL + "api/v1/json/"+BuildConfig.TSDB_API_KEY+"/searchevents.php?e="+constraint
        Assert.assertEquals(url, SportApiDB.getSearch(constraint))
    }
}