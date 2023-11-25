package com.chips.divisive.model

import androidx.compose.ui.graphics.Color
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Profile(
    @PrimaryKey val id: Int,
    val title: String,
)