package com.urbn.android.flickster.features.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urbn.android.flickster.Constants.FORMAT
import com.urbn.android.flickster.Constants.QUERY
import com.urbn.android.flickster.domain.model.Character
import com.urbn.android.flickster.domain.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListRepoImpl: CharacterListRepoImpl
) : ViewModel() {

    private val _characterList = MutableStateFlow<Response<List<Character>>>(Response.Loading())
    val characterList = _characterList.asStateFlow()
    var currentSortingMethod: SortingMethod = SortingMethod.ALPHABETICAL

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            _characterList.emit(characterListRepoImpl.getCharacterList(QUERY, FORMAT))
        }
    }

    fun sortAlphabetically(){
        viewModelScope.launch {
            characterList.value.data?.let {
                _characterList.emit(Response.Success(it.sortedBy { character ->
                    character.name
                }))
            }
        }
        currentSortingMethod = SortingMethod.ALPHABETICAL
    }

    fun sortReverseAlphabetically(){
        viewModelScope.launch {
            characterList.value.data?.let {
                _characterList.emit(Response.Success(it.sortedByDescending { character ->
                    character.name
                }))
            }
        }
        currentSortingMethod = SortingMethod.REVERSE_ALPHABETICAL
    }

    enum class SortingMethod {
        ALPHABETICAL,
        REVERSE_ALPHABETICAL
    }
}