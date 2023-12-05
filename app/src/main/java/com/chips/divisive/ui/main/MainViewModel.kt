package com.chips.divisive.ui.main

import androidx.compose.animation.VectorConverter
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileListState
import com.chips.divisive.repo.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
class MainViewModel @Inject constructor(
    private val repo: ProfileRepository
) : ViewModel() {


    private val _state: MutableStateFlow<ProfileListState> =
        MutableStateFlow(ProfileListState(isLoading = false))
    val stat = _state.asStateFlow()


    init {
        CoroutineScope(Dispatchers.IO).launch {

        }

        viewModelScope.launch {

            extracted(1)
            extracted(2)
            extracted(3)

            delay(500)
            findAll()
        }
    }

    private fun extracted(id: Int) {
        repo.insertProfile(item = Profile(id = id, title = "Profile $id"))
        repo.insertChip(
            ChipModel(
                value = "200#",
                count = 140,
                color = Color.Blue.value.toLong(),
                profileId = id
            )
        )
        repo.insertChip(
            ChipModel(
                value = "100#",
                count = 106,
                color = Color.Red.value.toLong(),
                profileId = id
            )
        )
        repo.insertChip(
            ChipModel(
                value = "50#",
                count = 97,
                color = Color.Yellow.value.toLong(),
                profileId = id
            )
        )
        repo.insertChip(
            ChipModel(
                value = "20#",
                count = 87,
                color = Color.Gray.value.toLong(),
                profileId = id
            )
        )
        repo.insertChip(
            ChipModel(
                value = "10#",
                color = Color.Cyan.value.toLong(),
                count = 300,
                profileId = id
            )
        )
    }


    private fun findAll() = viewModelScope.launch {
        repo.findAll().onStart { _state.update { state -> state.copy(isLoading = true) } }
            .onCompletion { _state.update { state -> state.copy(isLoading = false) } }
            .onEach {
                _state.update { state -> state.copy(value = it) }
            }.collect()
    }

}