package com.example.gallaryapp.di

import com.example.gallaryapp.data.repository.GalleryRepository
import com.example.gallaryapp.data.repository.GalleryRepositoryImpl
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