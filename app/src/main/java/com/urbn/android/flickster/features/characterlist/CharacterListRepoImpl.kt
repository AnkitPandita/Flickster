package com.urbn.android.flickster.features.characterlist

import com.urbn.android.flickster.ApiService
import com.urbn.android.flickster.domain.model.Character
import com.urbn.android.flickster.domain.util.Response
import com.urbn.android.flickster.network.mapper.RelatedTopicMapper
import javax.inject.Inject

class CharacterListRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: RelatedTopicMapper
) : CharacterListRepo {

    override suspend fun getCharacterList(q: String, format: String): Response<List<Character>> {
        val result = kotlin.runCatching { apiService.getData(q, format) }
        result.onSuccess {
            return Response.Success(mapper.toDomainList(it.relatedTopics).sortedBy { character ->
                character.name
            })
        }
        result.onFailure {
            return Response.Error(it.message.toString())
        }
        return Response.Error("Not responding!")
    }
}