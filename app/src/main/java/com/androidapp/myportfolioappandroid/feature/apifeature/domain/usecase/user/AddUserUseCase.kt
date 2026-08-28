package com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user

import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.repository.UserRepository
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.BaseUseCase
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<AddUser, AppResult<CreateUserResponse>>() {
    override suspend fun execute(params: AddUser): AppResult<CreateUserResponse> {
        return userRepository.addUser(params)
    }
}

//class AddUserUseCase @Inject constructor(
//    private val userRepository: UserRepository
//) : BaseUseCase<User, Flow<BaseUiState<User>>>() {
//    override suspend fun execute(params: User): Flow<BaseUiState<User>> {
//        return flow {
//            emit(BaseUiState.Loading)
//
//            val response = userRepository.addUser(params)
//
//            when (val result = response) {
//                is AppResult.Success -> {
//                    emit(
//                        BaseUiState.Success(result.data)
//                    )
//                }
//                is AppResult.Error -> {
//                    emit(
//                        BaseUiState.Error(result.error.toString())
//                    )
//                }
//            }
//        }
//    }
//}