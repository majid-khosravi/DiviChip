package com.chips.divisive.ui.main.dialog

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chips.divisive.model.ChipMakerState
import com.chips.divisive.model.ChipModel
import com.chips.divisive.repo.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChipViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: ProfileRepository,
) : ViewModel() {

    private val chipId: Int = 7

    private val profileId: Int = 2

    /*

    private val chipId: Int =
        savedStateHandle["chipId"] ?: throw IllegalStateException("chipId is required")

    private val profileId: Int =
        savedStateHandle["profileId"] ?: throw IllegalStateException("profileId is required")*/

    /*  private val _state: MutableStateFlow<ChipMakerState> = MutableStateFlow(ChipMakerState())
      val state = _state*/


    private val _state = MutableStateFlow(ChipMakerState())
    val state = _state


    /*


        private val _registerState = MutableStateFlow(RegisterState())
        val registerState = _registerState*/

    /*
        private val _chipState = MutableStateFlow(
            ChipMakerState(
                isLoading = false,
                value = ChipModel(profileId = profileId, id = chipId)
            )
        )
        val chipState = _chipState*/

    /*
        private val _interState: MutableStateFlow<InsertChipState> =
            MutableStateFlow(InsertChipState(isLoading = false))
        val interState = _interState.asStateFlow()
    */

    init {
        findChipById(chipId)
    }


    /*
        fun findChipById2(id: Int) = viewModelScope.launch {
            repo.findChipById(id)
                .onStart { _state.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
                .onEach {
                    it?.let {
                        _state.update { state ->
                            state.copy(
                                value = it,
                                isLoading = false,
                                isSuccess = false
                            )
                        }
    //                    _state.value = ChipMakerState(value = it, isLoading = false, isSuccess = false)
                    }
                }.collect()

        }
    */

    fun findChipById(id: Int) {
        viewModelScope.launch {
            repo.findChipById(id).collect {
                Log.e("TAG", "ms--findChipById: $it")

                it?.let {
                    _state.update { state ->
                        state.copy(
                            value = it
                        )
                    }
//                    _state.value = ChipMakerState(value = it, isLoading = false, isSuccess = false)
                }
            }
        }
    }

    /*
        fun findProfileById(id: Int) = viewModelScope.launch {
            repo.findById(id).onStart { _state.update { state -> state.copy(isLoading = true) } }
                .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
                .onEach {
                    _state.update { state -> state.copy(value = it) }
                }.collect()
        }
    */


    /* fun findProfileById(id: Int) = viewModelScope.launch {
         repo.findById(id).onStart { _state.update { state -> state.copy(isLoading = true) } }
             .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
             .onEach {
                 _state.update { state -> state.copy(value = it) }
             }.collect()
     }*/

    fun insertChip(chip: ChipModel) = viewModelScope.launch {
        chip.profileId = profileId
        repo.updateChip(chip).collect {
            Log.d("TAG", "insertedChip: $it")
        }
        /* .onStart { _state.update { state -> state.copy(isLoading = true) } }
         .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
         .onEach {
             _state.update { state -> state.copy(isSuccess = !it.equals(0)) }
         }.collect()*/
    }

    fun updateValue(newValue: String) {
        _state.update { state ->
            state.copy(value = state.value?.updateChipValue(newValue))
        }
    }

    fun updateCount(newCount: Int) {
        _state.update { state ->
            state.copy(value = state.value?.updateChipCount(newCount))
        }
    }

    fun updateColor(newColor: Color) {
        _state.update { state ->
            state.copy(value = state.value?.updateChipColor(newColor))
        }
    }

    private fun ChipModel.updateChipColor(newColor: Color): ChipModel {
        return ChipModel(
            id = id,
            profileId = profileId,
            value = value,
            count = count,
            color = newColor.value.toLong(),
            textColor = textColor
        )
    }

    private fun ChipModel.updateChipTextColor(newColor: Color): ChipModel {
        return ChipModel(
            id = id,
            profileId = profileId,
            value = value,
            count = count,
            color = color,
            textColor = newColor.value.toLong()
        )
    }

    private fun ChipModel.updateChipValue(newValue: String): ChipModel {
        return ChipModel(
            id = id,
            profileId = profileId,
            value = newValue,
            count = count,
            color = color,
            textColor = textColor
        )
    }

    private fun ChipModel.updateChipCount(newCount: Int): ChipModel {
        return ChipModel(
            id = id,
            profileId = profileId,
            value = value,
            count = newCount,
            color = color,
            textColor = textColor
        )
    }

    fun updateTextColor(newTextColor: Color) {
        _state.update { state ->
            state.copy(value = state.value?.updateChipTextColor(newTextColor))
        }
        /*  _state.update { state ->
              state.copy(value = state.value?.apply {
                  textColor = newTextColor.value.toLong()
              })
          }*/
    }


}