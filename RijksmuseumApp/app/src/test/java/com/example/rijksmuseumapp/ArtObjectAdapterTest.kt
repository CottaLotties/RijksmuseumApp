package com.example.rijksmuseumapp

import com.example.rijksmuseumapp.entity.ArtObject
import com.example.rijksmuseumapp.ui.main.ArtObjectAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ArtObjectAdapterTest {

    private lateinit var adapter: ArtObjectAdapter

    @Mock
    private lateinit var mockListener: ArtObjectAdapter.OnArtObjectClickListener

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        adapter = ArtObjectAdapter(mockListener)
    }

    @Test
    fun testAddData() {
        val artObjects = listOf(
            ArtObject("id1", "title1", null, null),
            ArtObject("id2", "title2", null, null)
        )

        adapter.addData(artObjects)

        assertEquals(adapter.itemCount, artObjects.size)
    }
}
