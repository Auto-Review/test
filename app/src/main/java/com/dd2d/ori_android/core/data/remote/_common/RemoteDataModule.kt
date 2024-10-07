package com.dd2d.ori_android.core.data.remote._common

import com.dd2d.ori_android.core.data.remote.code.repository.CodeRepository
import com.dd2d.ori_android.core.data.remote.code.repository.CodeRepositoryTest
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {

    @Binds
    @Singleton
    abstract fun bindCodeRepository(impl: CodeRepositoryTest): CodeRepository
}