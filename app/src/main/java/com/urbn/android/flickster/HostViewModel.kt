package com.urbn.android.flickster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HostViewModel: ViewModel() {

    private val _appBarTitle = MutableSharedFlow<String>()
    val appBarTitle = _appBarTitle.asSharedFlow()

    fun updateAppBarTitle(title: String) {
        viewModelScope.launch {
            _appBarTitle.emit(title)
        }
    }
}