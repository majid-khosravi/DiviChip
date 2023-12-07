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

    override fun insertProfile(item: Profile): Flow<Unit> {
//        return dataSource.insertProfile(item)
        return flow {
            emit(dataSource.insertProfile(item))
        }
    }

    override fun insertChip(item: ChipModel): Flow<Unit> {
//        dataSource.insertChip(item)
        return flow {
            emit(dataSource.insertChip(item))
        }
    }

    override fun update(item: Profile): Flow<Unit> {
//        return dataSource.update(item)
        return flow {
            emit(dataSource.update(item))
        }
    }


    override fun updateChip(item: ChipModel): Flow<Unit> {
//        return dataSource.update(item)
        return flow {
            emit(dataSource.updateChip(item))
        }
    }

    override fun delete(item: Profile): Flow<Unit> {
//        return dataSource.delete(item)
        return flow {
            emit(dataSource.delete(item))
        }
    }
}