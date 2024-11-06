package com.luckyfrog.quickmart.features.product.domain.usecases

import androidx.paging.PagingData
import com.luckyfrog.quickmart.core.generic.usecase.UseCase
import com.luckyfrog.quickmart.features.product.domain.entities.ProductEntity
import com.luckyfrog.quickmart.features.product.domain.entities.ProductFormParamsEntity
import com.luckyfrog.quickmart.features.product.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) : UseCase<ProductFormParamsEntity, Flow<PagingData<ProductEntity>>> {
    override suspend fun execute(input: ProductFormParamsEntity): Flow<PagingData<ProductEntity>> {
        return repository.getProductsByCategory(params = input)
    }
}