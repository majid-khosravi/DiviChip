package com.chips.divisive.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile

@Database(entities = [Profile::class, ChipModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun profilesDAO(): ProfilesDAO

}