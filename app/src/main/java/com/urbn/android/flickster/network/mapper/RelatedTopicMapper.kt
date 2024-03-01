package com.urbn.android.flickster.network.mapper

import com.urbn.android.flickster.domain.model.Character
import com.urbn.android.flickster.domain.util.Mapper
import com.urbn.android.flickster.network.model.RelatedTopic
import javax.inject.Inject

class RelatedTopicMapper @Inject constructor(): Mapper<RelatedTopic, Character> {

    override fun mapToDomainModel(dto: RelatedTopic): Character {
        val stringList = dto.text.trim().split(" - ")
        return Character(
            name = if (stringList.isNotEmpty()) stringList[0] else "",
            details = if (stringList.size > 1) stringList[1] else "",
            imageUrl = dto.icon.url
        )
    }

    fun toDomainList(list: List<RelatedTopic>): List<Character> {
        return list.map { mapToDomainModel(it) }
    }
}