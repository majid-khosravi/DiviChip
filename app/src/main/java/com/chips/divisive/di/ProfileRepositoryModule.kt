package com.chips.divisive.di

import com.chips.divisive.repo.ProfileRepository
import com.chips.divisive.repo.ProfileRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileRepositoryModule {

    @Binds
    abstract fun bindsProfileRepository(profileRepositoryImp: ProfileRepositoryImp): ProfileRepository

}
