package com.chips.divisive.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChipModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var value: String = "",
    var count: Int = 0,
    var profileId: Int,
    var color: Long = Color.Gray.value.toLong(),
    var textColor: Long = Color.Black.value.toLong(),
)
