package com.chips.divisive.ds

import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips

interface ProfilesDataSource {

    suspend fun findAll(): List<ProfileWithItsChips>

    suspend fun findById(id: Int): ProfileWithItsChips?
    suspend fun findChipById(id: Int): ChipModel?
    suspend fun delete(item: Profile)
    suspend fun update(item: Profile)
    suspend fun updateChip(item: ChipModel)
    suspend fun insertChip(item: ChipModel)
    suspend fun insertProfile(item: Profile)
}