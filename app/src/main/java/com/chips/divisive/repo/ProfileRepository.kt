package com.chips.divisive.repo

import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun findAll(): Flow<List<ProfileWithItsChips>>

    fun findById(id: Int): Flow<ProfileWithItsChips?>

    fun insertProfile(item: Profile)
    fun insertChip(item: ChipModel)

    fun update(item: Profile)

    fun delete(item: Profile)
}