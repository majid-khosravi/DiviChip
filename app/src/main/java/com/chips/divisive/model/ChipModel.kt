package com.chips.divisive.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChipModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: String,
    val count: Int,
    val profileId: Int,
    val color: Long
)
