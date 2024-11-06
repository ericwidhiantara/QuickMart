package com.luckyfrog.quickmart.features.product.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.esotericsoftware.kryo.util.Util.log
import com.luckyfrog.quickmart.features.product.data.datasources.remote.ProductRemoteDataSource
import com.luckyfrog.quickmart.features.product.data.models.mapper.toEntityList
import com.luckyfrog.quickmart.features.product.domain.entities.ProductEntity
import com.luckyfrog.quickmart.features.product.domain.entities.ProductFormParamsEntity
import com.luckyfrog.quickmart.utils.helper.Constants
import retrofit2.HttpException
import java.io.IOException

class ProductByCategoryPagingSource(
    private val remoteDataSource: ProductRemoteDataSource,
    private val formParams: ProductFormParamsEntity
) : PagingSource<Int, ProductEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntity> {
        return try {
            val currentPage = params.key ?: 1

            val form = ProductFormParamsEntity(
                limit = Constants.MAX_PAGE_SIZE,
                skip = (currentPage - 1) * Constants.MAX_PAGE_SIZE,
                category = formParams.category,
                order = formParams.order,
                sortBy = formParams.sortBy,
                q = null,
            )
            val result = remoteDataSource.getProductsByCategory(form)
            log("result: $result", "ProductByCategoryPagingSource")
            LoadResult.Page(
                data = if (result.data != null) result.data.toEntityList() else emptyList(),
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (result.data!!.size < Constants.MAX_PAGE_SIZE) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductEntity>): Int? {
        return state.anchorPosition
    }

}