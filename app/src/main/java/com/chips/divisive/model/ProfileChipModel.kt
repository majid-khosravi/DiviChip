package com.chips.divisive.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "group_user_table",
    primaryKeys = ["chip_id", "profile_id"],
    foreignKeys = [
        ForeignKey(
            entity = ChipModel::class,
            parentColumns = ["chip_id"],
            childColumns = ["chip_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Profile::class,
            parentColumns = ["profile_id"],
            childColumns = ["profile_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProfileChipModel(
    @ColumnInfo(name = "profile_id", index = true)
    val groupId: String,
    @ColumnInfo(name = "chip_id", index = true)
    val userId: String
)