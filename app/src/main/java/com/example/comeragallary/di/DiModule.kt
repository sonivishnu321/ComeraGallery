package com.example.comeragallary.di

import com.example.comeragallary.data.repository.GalleryRepository
import com.example.comeragallary.data.repository.GalleryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {

    @Binds
    @Singleton
    abstract fun provideGalleryRepository(repository: GalleryRepositoryImpl) : GalleryRepository
}