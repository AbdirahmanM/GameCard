package com.example.gamelb

import com.example.gamelb.api.models.Genre
import com.example.gamelb.api.models.Platform
import com.example.gamelb.api.models.Store
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilFunctionsTest {

    @Test
    fun converListOfObjectsToStringTest() {
        val genres: List<Genre> = listOf(Genre("Action"), Genre("Horror"), Genre("Supernatural"))
        val platforms: List<Platform> = listOf(Platform("Playstation"), Platform("Xbox"), Platform("PC"))
        val stores: List<Store> = listOf(Store("Epic Games", ""), Store("Playstation Store", ""))
        val strings: List<String> = listOf("String1", "String2", "String3")
        assertEquals("Action, Horror, Supernatural", convertListOfObjectsToString(genres))
        assertEquals("Playstation, Xbox, PC", convertListOfObjectsToString(platforms))
        assertEquals("Epic Games, Playstation Store", convertListOfObjectsToString(stores))
        assertEquals("", convertListOfObjectsToString(strings))
    }

    @Test
    fun formateDateTest(){
        val date1 = "2018-04-07"
        val date2 = "2009-06-23"
        assertEquals("7 April 2018", formatDate(date1))
        assertEquals("23 Juni 2009", formatDate(date2))
    }

    @Test
    fun checkDate(){
        val date1 = "2018-04-07"
        val date2 = "2009-06-23"
        val date3 = "2022-08-02"
        assertEquals(checkDate(date1), false)
        assertEquals(checkDate(date2), false)
        assertEquals(checkDate(date3), true)
    }
}