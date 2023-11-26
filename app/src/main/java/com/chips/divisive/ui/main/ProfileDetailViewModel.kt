package com.chips.divisive.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chips.divisive.model.ProfilesDetailState
import com.chips.divisive.repo.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: ProfileRepository
) : ViewModel() {

    private val profileId: Int =
        savedStateHandle["profileId"] ?: throw IllegalStateException("profileId is required")

    private val _state: MutableStateFlow<ProfilesDetailState> =
        MutableStateFlow(ProfilesDetailState(isLoading = false))
    val stat = _state.asStateFlow()

    init {
        findProfileById(profileId)
    }

    fun findProfileById(id: Int) = viewModelScope.launch {
        repo.findById(id).onStart { _state.update { state -> state.copy(isLoading = true) } }
            .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
            .onEach {
                _state.update { state -> state.copy(value = it) }
            }.collect()
    }

}