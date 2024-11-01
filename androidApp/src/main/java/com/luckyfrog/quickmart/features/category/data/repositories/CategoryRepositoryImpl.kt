package com.luckyfrog.quickmart.features.category.data.repositories

import com.luckyfrog.quickmart.features.category.data.datasources.remote.CategoryRemoteDataSource
import com.luckyfrog.quickmart.features.category.data.models.mapper.toEntityList
import com.luckyfrog.quickmart.features.category.domain.entities.CategoryEntity
import com.luckyfrog.quickmart.features.category.domain.repositories.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource
) : CategoryRepository {
    
    override suspend fun getCategories(): List<CategoryEntity> {
        return remoteDataSource.getCategories().toEntityList()
    }
}