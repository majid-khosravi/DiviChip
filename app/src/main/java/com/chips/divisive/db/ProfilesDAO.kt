package com.chips.divisive.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.flow.Flow


@Dao
interface ProfilesDAO {

    @Transaction
    @Query("SELECT * FROM profile")
    fun getCategoryWithItsQuotes(): List<ProfileWithItsChips>


    @Query("SELECT * FROM profile")
    fun findAll(): List<Profile>


    @Query("SELECT * FROM profile WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): ProfileWithItsChips?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(item: Profile)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertChip(item: ChipModel)


    @Update
    fun update(item: Profile)

    @Delete
    fun delete(item: Profile)

    @Query("DELETE FROM profile")
    fun truncate()
}