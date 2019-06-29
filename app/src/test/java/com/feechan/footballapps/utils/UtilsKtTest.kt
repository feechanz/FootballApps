package com.feechan.footballapps.utils

import junit.framework.Assert
import org.junit.Test
import java.text.SimpleDateFormat

class UtilsKtTest {
    @Test
    fun testToSimpleString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("09/24/2018")
        Assert.assertEquals("Mon, 24 Sep 2018", toSimpleString(date))
    }
}