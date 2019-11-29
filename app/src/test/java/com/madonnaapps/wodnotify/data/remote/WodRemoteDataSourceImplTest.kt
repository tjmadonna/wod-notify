package com.madonnaapps.wodnotify.data.remote

import com.madonnaapps.wodnotify.common.types.Result
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.remote.mappers.WodNetworkResponseItemMapper
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponse
import com.madonnaapps.wodnotify.data.remote.models.WodNetworkResponseItem
import com.madonnaapps.wodnotify.data.remote.services.WodRemoteService
import com.madonnaapps.wodnotify.test.WodEntityFactory
import com.madonnaapps.wodnotify.test.WodNetworkResponseItemFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WodRemoteDataSourceImplTest {

    @Mock
    lateinit var mockWodRemoteService: WodRemoteService

    @Mock
    lateinit var mockMapper: WodNetworkResponseItemMapper

    lateinit var remoteDataSource: WodRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = WodRemoteDataSourceImpl(mockWodRemoteService, mockMapper)
    }

    @Test
    fun getWodsReturnsWods() = runBlocking {

        val expectedEntity = WodEntityFactory.makeWodEntity()

        val mockNetworkResponseItem = WodNetworkResponseItemFactory.makeWodNetworkResponseItemList(5)
        val mockNetworkResponse = WodNetworkResponse(mockNetworkResponseItem)
        stubWodRemoteServiceGetWods(mockNetworkResponse)
        stubWodNetworkResponseItemMapperMapToWodEntity(expectedEntity)

        val actualResult = remoteDataSource.getWods()

        assertTrue(actualResult is Result.Success)
        val actualEntities = (actualResult as Result.Success).data
        for (index in 0..4) {
            assertEquals(expectedEntity, actualEntities[index])
        }
    }

    private fun stubWodRemoteServiceGetWods(networkResponse: WodNetworkResponse?) = runBlocking {
        whenever(mockWodRemoteService.getWods()).thenReturn(networkResponse)
    }

    private fun stubWodNetworkResponseItemMapperMapToWodEntity(
        wodEntity: WodEntity,
        item: WodNetworkResponseItem = any()
    ) {
        whenever(mockMapper.mapToWodEntity(item)).thenReturn(wodEntity)
    }

}