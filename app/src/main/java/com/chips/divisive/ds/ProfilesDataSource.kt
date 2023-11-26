package com.chips.divisive.ds

import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.flow.Flow

interface ProfilesDataSource {

    fun findAll(): List<ProfileWithItsChips>

    fun findById(id: Int): ProfileWithItsChips?
    fun findChipById(id: Int): ChipModel?
    fun delete(item: Profile)
    fun update(item: Profile)
    fun insertChip(item: ChipModel): Long
    fun insertProfile(item: Profile)
}