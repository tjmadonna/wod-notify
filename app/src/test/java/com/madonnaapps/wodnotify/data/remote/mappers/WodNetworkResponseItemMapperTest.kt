package com.madonnaapps.wodnotify.data.remote.mappers

import com.madonnaapps.wodnotify.test.DataFactory
import com.madonnaapps.wodnotify.test.WodNetworkResponseItemFactory
import com.madonnaapps.wodnotify.test.extensions.midnightTimeOfCurrentDay
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class WodNetworkResponseItemMapperTest {

    private lateinit var mapper: WodNetworkResponseItemMapper
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
        mapper = WodNetworkResponseItemMapper(dateFormats, baseUrl)
    }

    @Test
    fun mapToWodEntityWithMMDDYYYYDateFormatReturnsValue() {
        // Create test values
        val testResponseItem = WodNetworkResponseItemFactory.makeWodNetworkResponseItem(
            mmddyyyyDateFormat.format(date)
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
            "QQ-${DataFactory.randomString()}" // This should be unparsable
        )

        // Test
        val actualValue = mapper.mapToWodEntity(testResponseItem)

        // Assert
        assertNull(actualValue)
    }

}