package com.chips.divisive.ds

import com.chips.divisive.db.ProfilesDAO
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileLocalDataSource @Inject constructor(private val profilesDAO: ProfilesDAO) :
    ProfilesDataSource {
    override suspend fun findAll(): List<ProfileWithItsChips> {
        return profilesDAO.getCategoryWithItsQuotes()
    }

    override suspend fun findById(id: Int): ProfileWithItsChips? {
        return profilesDAO.findById(id)
//        return profilesDAO.getCategoryWithItsQuotes(1)

    }

    override suspend fun findChipById(id: Int): ChipModel? {
       /* CoroutineScope(Dispatchers.IO).async {
//            profilesDAO.insertChip(item)
            return@async profilesDAO.findChipById(id)
        }*/
        return profilesDAO.findChipById(id)
//        return null

    }

    override suspend fun insertProfile(item: Profile) {
        CoroutineScope(Dispatchers.IO).launch {
            profilesDAO.insertProfile(item)
        }
//        profilesDAO.insert(item.chips)
    }

    override suspend fun insertChip(item: ChipModel) {

        CoroutineScope(Dispatchers.IO).launch {
            profilesDAO.insertChip(item)
        }
        /*  val i =profilesDAO.insertChip(item)
          Log.d("TAG", "insertChip: $i")
          return i*/

//        profilesDAO.insert(item.chips)
    }

    override suspend fun update(item: Profile) {
        profilesDAO.update(item)
    }

    override suspend fun updateChip(item: ChipModel) {
        profilesDAO.updateChip(item)
    }

    override suspend fun delete(item: Profile) {
        profilesDAO.delete(item)
    }


}