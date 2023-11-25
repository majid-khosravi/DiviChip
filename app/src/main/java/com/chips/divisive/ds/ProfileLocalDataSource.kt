package com.chips.divisive.ds

import com.chips.divisive.db.ProfilesDAO
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileLocalDataSource @Inject constructor(private val profilesDAO: ProfilesDAO) :
    ProfilesDataSource {
    override fun findAll(): List<ProfileWithItsChips> {
        return profilesDAO.getCategoryWithItsQuotes()
    }

    override fun findById(id: Int): ProfileWithItsChips? {
        return profilesDAO.findById(id)
//        return profilesDAO.getCategoryWithItsQuotes(1)

    }

    override fun insertProfile(item: Profile) {
        CoroutineScope(Dispatchers.IO).launch{
            profilesDAO.insertProfile(item)
        }
//        profilesDAO.insert(item.chips)
    }

    override fun insertChip(item: ChipModel) {

        CoroutineScope(Dispatchers.IO).launch{
            profilesDAO.insertChip(item)
        }
//        profilesDAO.insert(item.chips)
    }

    override fun update(item: Profile) {
        profilesDAO.update(item)
    }

    override fun delete(item: Profile) {
        profilesDAO.delete(item)
    }


}