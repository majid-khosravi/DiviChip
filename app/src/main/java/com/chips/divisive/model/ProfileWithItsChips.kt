package com.chips.divisive.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProfileWithItsChips(
    @Embedded
    val profile: Profile,
    @Relation(
        entity = ChipModel::class,
        parentColumn = "id",
        entityColumn = "profileId",
    )
    val chips: List<ChipModel>
)