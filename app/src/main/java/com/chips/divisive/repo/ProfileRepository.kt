package com.chips.divisive.repo

import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun findAll(): Flow<List<ProfileWithItsChips>>

    fun findById(id: Int): Flow<ProfileWithItsChips?>

    fun findChipById(id: Int): Flow<ChipModel?>

    fun insertProfile(item: Profile): Flow<Unit>
    fun insertChip(item: ChipModel): Flow<Unit>

    fun update(item: Profile): Flow<Unit>
    fun updateChip(item: ChipModel): Flow<Unit>

    fun delete(item: Profile): Flow<Unit>
}