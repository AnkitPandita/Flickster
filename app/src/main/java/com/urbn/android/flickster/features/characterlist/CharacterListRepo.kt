package com.urbn.android.flickster.features.characterlist

import com.urbn.android.flickster.domain.model.Character
import com.urbn.android.flickster.domain.util.Response

interface CharacterListRepo {

    suspend fun getCharacterList(q: String, format: String): Response<List<Character>>
}