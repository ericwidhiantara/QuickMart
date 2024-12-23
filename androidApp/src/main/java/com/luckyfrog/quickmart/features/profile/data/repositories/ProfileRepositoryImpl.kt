package com.luckyfrog.quickmart.features.profile.data.repositories

import com.luckyfrog.quickmart.core.generic.dto.ResponseDto
import com.luckyfrog.quickmart.core.network.processResponse
import com.luckyfrog.quickmart.features.profile.data.datasources.remote.ProfileRemoteDataSource
import com.luckyfrog.quickmart.features.profile.data.models.request.ChangePasswordFormRequestDto
import com.luckyfrog.quickmart.features.profile.data.models.request.CheckPasswordFormRequestDto
import com.luckyfrog.quickmart.features.profile.domain.repositories.ProfileRepository
import com.luckyfrog.quickmart.utils.helper.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun checkPassword(params: CheckPasswordFormRequestDto): Flow<ApiResponse<ResponseDto<Unit>>> =
        flow {
            emit(ApiResponse.Loading)
            val response = remoteDataSource.checkPassword(params)
            emit(processResponse(response) { })
        }

    override suspend fun changePassword(params: ChangePasswordFormRequestDto): Flow<ApiResponse<ResponseDto<Unit>>> =
        flow {
            emit(ApiResponse.Loading)
            val response = remoteDataSource.changePassword(params)
            emit(processResponse(response) { })
        }

}
