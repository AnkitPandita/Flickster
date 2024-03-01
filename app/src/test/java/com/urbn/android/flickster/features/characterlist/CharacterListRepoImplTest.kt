package com.urbn.android.flickster.features.characterlist

import com.nhaarman.mockitokotlin2.any
import com.urbn.android.flickster.ApiService
import com.urbn.android.flickster.network.mapper.RelatedTopicMapper
import com.urbn.android.flickster.network.model.ApiResponseData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.urbn.android.flickster.domain.model.Character
import kotlinx.coroutines.test.runTest

/**
 * Test class for repository.
 */
class CharacterListRepoImplTest {

    @InjectMocks
    lateinit var sut: CharacterListRepoImpl

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var mapper: RelatedTopicMapper

    @Mock
    lateinit var apiResponseData: ApiResponseData

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetCharacterList() = runTest {
        val characterList = listOf(
            Character("name0", "details0", null, false),
            Character("name4", "details4", null, false),
            Character("name3", "details3", null, false),
            Character("name2", "details2", null, false),
            Character("name1", "details1", null, false)
        )
        Mockito.`when`(apiService.getData(any(), any())).thenReturn(apiResponseData)
        Mockito.`when`(mapper.toDomainList(any())).thenReturn(characterList)
        val response = sut.getCharacterList("abc", "xyz")
        assertEquals(true, response.data!!.size == 5)
        assertEquals("name3", response.data!![3].name)
    }
}