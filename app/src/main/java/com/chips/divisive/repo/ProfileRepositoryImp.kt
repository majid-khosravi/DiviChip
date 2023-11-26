package com.chips.divisive.repo

import com.chips.divisive.ds.ProfileLocalDataSource
import com.chips.divisive.model.ChipModel
import com.chips.divisive.model.Profile
import com.chips.divisive.model.ProfileWithItsChips
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImp @Inject constructor(private val dataSource: ProfileLocalDataSource) :
    ProfileRepository {
    override fun findAll(): Flow<List<ProfileWithItsChips>> {
        return flow {
            emit(dataSource.findAll())
        }
    }

    override fun findById(id: Int): Flow<ProfileWithItsChips?> {
        return flow {
            emit(dataSource.findById(id))
        }
    }


    override fun findChipById(id: Int): Flow<ChipModel?> {
        return flow {
            emit(dataSource.findChipById(id))
        }
    }

    override fun insertProfile(item: Profile) {
        return dataSource.insertProfile(item)
    }

    override fun insertChip(item: ChipModel): Flow<Long> {
        return flow { dataSource.insertChip(item) }
    }

    override fun update(item: Profile) {
        return dataSource.update(item)
    }

    override fun delete(item: Profile) {
        return dataSource.delete(item)
    }
}