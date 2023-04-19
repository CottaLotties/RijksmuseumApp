package com.example.rijksmuseumapp

import com.example.rijksmuseumapp.database.AppDatabase
import com.example.rijksmuseumapp.database.ArtObjectDao
import com.example.rijksmuseumapp.entity.ArtObject
import com.example.rijksmuseumapp.entity.WebImage
import com.example.rijksmuseumapp.network.ArtObjectService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito

class ArtObjectRepositoryTest {

    @Mock
    private lateinit var dao: ArtObjectDao

    @Mock
    private lateinit var service: ArtObjectService

    @Mock
    private lateinit var database: AppDatabase

    private lateinit var repository: ArtObjectRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = ArtObjectRepository(dao, service, database)
    }

    @Test
    fun testGetCachedObjects() = runBlocking {
        // Prepare test data
        val currentPage = 1
        val pageSize = 10
        val cachedArtObjects = listOf(ArtObject("1", "Title 1", "Artist 1", WebImage("1", "url", 1, 1, 0, 0)))

        // Mock DAO and Database
        Mockito.`when`(dao.getArtObjects()).thenReturn(cachedArtObjects)

        // Call the function to be tested
        val result = repository.getArtObjects(currentPage, pageSize)

        // Verify the expected results
        assertEquals(cachedArtObjects, result)
    }
}