package com.chips.divisive.ui.main.dialog

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chips.divisive.model.ChipMakerState
import com.chips.divisive.model.ChipModel
import com.chips.divisive.repo.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChipViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: ProfileRepository,
) : ViewModel() {

    private val chipId: Int =
        savedStateHandle["chipId"] ?: throw IllegalStateException("chipId is required")

    private val profileId: Int =
        savedStateHandle["profileId"] ?: throw IllegalStateException("profileId is required")

    private val _state: MutableStateFlow<ChipMakerState> =
        MutableStateFlow(
            ChipMakerState(
                isLoading = false,
                value = ChipModel(profileId = profileId, id = chipId)
            )
        )
    val state = _state.asStateFlow()

    /*
        private val _interState: MutableStateFlow<InsertChipState> =
            MutableStateFlow(InsertChipState(isLoading = false))
        val interState = _interState.asStateFlow()
    */

    init {
        findChipById(chipId)
    }

    fun findChipById(id: Int) = viewModelScope.launch {
        repo.findChipById(id)
            .onStart { _state.update { state -> state.copy(isLoading = true) } }
            .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
            .onEach {
                it?.let {
                    _state.update { state -> state.copy(value = it) }
                }
            }

    }

    fun insertChip(chip: ChipModel) = viewModelScope.launch {
        chip.profileId = profileId
        repo.insertChip(chip)
        /* .onStart { _state.update { state -> state.copy(isLoading = true) } }
         .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
         .onEach {
             _state.update { state -> state.copy(isSuccess = !it.equals(0)) }
         }.collect()*/
    }


}