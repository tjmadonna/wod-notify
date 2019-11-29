package com.madonnaapps.wodnotify.data.remote.mappers

import com.madonnaapps.wodnotify.test.DataFactory
import com.madonnaapps.wodnotify.test.WodNetworkResponseItemFactory
import com.madonnaapps.wodnotify.test.extensions.midnightTimeOfCurrentDay
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class WodNetworkResponseItemMapperImplTest {

    private lateinit var mapper: WodNetworkResponseItemMapperImpl
    private lateinit var baseUrl: String
    private lateinit var date: Date

    // Date Formats
    private val mmddyyyyDateFormat = SimpleDateFormat("MMddyyyy", Locale.US)

    private val dateFormats = arrayOf(
        mmddyyyyDateFormat
    )

    @Before
    fun setup() {
        baseUrl = DataFactory.randomString()
        date = Calendar.getInstance().midnightTimeOfCurrentDay
        mapper = WodNetworkResponseItemMapperImpl(dateFormats, baseUrl)
    }

    @Test
    fun mapToWodEntityWithMMDDYYYYDateFormatReturnsValue() {
        // Create test values
        val testResponseItem = WodNetworkResponseItemFactory.makeWodNetworkResponseItem(
            title = mmddyyyyDateFormat.format(date)
        )

        // Test
        val actualValue = mapper.mapToWodEntity(testResponseItem)

        // Assert
        assertNotNull(actualValue)
        assertEquals(testResponseItem.id, actualValue!!.id)
        assertEquals(date, actualValue.date)
        assertEquals(testResponseItem.title, actualValue.title)
        assertEquals(testResponseItem.author!!.name, actualValue.author)
        assertEquals("${baseUrl}${testResponseItem.relativeUrl}", actualValue.url)
        assertEquals(testResponseItem.description, actualValue.description)
    }

    @Test
    fun mapToWodEntityWithUnknownDateFormatReturnsNull() {
        // Create test values
        val testResponseItem = WodNetworkResponseItemFactory.makeWodNetworkResponseItem(
            title = "QQ-${DataFactory.randomString()}" // This should be unparsable
        )

        // Test
        val actualValue = mapper.mapToWodEntity(testResponseItem)

        // Assert
        assertNull(actualValue)
    }

    @Test
    fun mapToWodEntityWithNullRelativeUrlReturnsNull() {
        // Create test values
        val testResponseItem = WodNetworkResponseItemFactory.makeWodNetworkResponseItem(
            title = mmddyyyyDateFormat.format(date),
            relativeUrl = null
        )

        // Test
        val actualValue = mapper.mapToWodEntity(testResponseItem)

        // Assert
        assertNull(actualValue)
    }

}