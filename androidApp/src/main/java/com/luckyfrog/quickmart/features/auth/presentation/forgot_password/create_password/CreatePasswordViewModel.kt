package com.luckyfrog.quickmart.features.auth.presentation.forgot_password.create_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luckyfrog.quickmart.core.generic.entities.MetaEntity
import com.luckyfrog.quickmart.core.generic.mapper.toEntity
import com.luckyfrog.quickmart.features.auth.data.models.request.ForgotPasswordChangePasswordFormRequestDto
import com.luckyfrog.quickmart.features.auth.domain.usecases.ForgotPasswordChangePasswordUseCase
import com.luckyfrog.quickmart.utils.helper.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CreatePasswordState {
    data object Idle : CreatePasswordState()
    data object Loading : CreatePasswordState()
    data class Success(val data: MetaEntity) : CreatePasswordState()
    data class Error(val message: String) : CreatePasswordState()
}

@HiltViewModel
class CreatePasswordViewModel @Inject constructor(
    private val _usecase: ForgotPasswordChangePasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CreatePasswordState>(CreatePasswordState.Idle)
    val state: StateFlow<CreatePasswordState> = _state

    fun changePassword(params: ForgotPasswordChangePasswordFormRequestDto) {
        viewModelScope.launch {
            _usecase.execute(params).collect { response ->
                when (response) {
                    is ApiResponse.Loading -> {
                        _state.value = CreatePasswordState.Loading
                    }

                    is ApiResponse.Success -> {
                        _state.value = CreatePasswordState.Success(response.data.meta?.toEntity()!!)
                    }

                    is ApiResponse.Failure -> {
                        _state.value = CreatePasswordState.Error(response.errorMessage)
                    }
                }
            }
        }
    }

}
