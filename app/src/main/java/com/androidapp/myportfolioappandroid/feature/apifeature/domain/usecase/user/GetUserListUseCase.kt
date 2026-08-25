package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserListResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<BaseUiState<List<User>>> {
        return flow {
            emit(BaseUiState.Loading)

            when (val result = userRepository.getUserList()) {
                is AppResult.Success -> {
                    emit(
                        BaseUiState.Success(result.data)
                    )
                }

                is AppResult.Error -> {
                    emit(
                        BaseUiState.Error(result.error.toString())
                    )
                }
            }
        }
    }
}