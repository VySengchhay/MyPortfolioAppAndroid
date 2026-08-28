package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.core.common.toMessage
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.request.UserApiRequest
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.CreateUserResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.AddUserUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserApiViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {
    private val _userList = MutableStateFlow<BaseUiState<List<User>>?>(null)
    val userList: StateFlow<BaseUiState<List<User>>?> = _userList.asStateFlow()

    private val _createUserUiState = MutableStateFlow<BaseUiState<CreateUserResponse>?>(null)
    val uiState: StateFlow<BaseUiState<CreateUserResponse>?> = _createUserUiState.asStateFlow()

    init {
        getUserList()
    }

    fun getUserList() {
        _userList.value = BaseUiState.Loading

        viewModelScope.launch {
            when (val result = getUserListUseCase()) {
                is AppResult.Success -> {
                    _userList.value = BaseUiState.Success(result.data)
                }

                is AppResult.Error -> {
                    _userList.value = BaseUiState.Error(result.error.toMessage())
                }
            }
        }
    }

    fun addUser(
        name: String,
        email: String
    ) {
        val body = AddUser(
            name = name,
            email = email
        )

        viewModelScope.launch {
            _createUserUiState.update {
                BaseUiState.Loading
            }

            when (val result = addUserUseCase(body)) {
                is AppResult.Success -> {
                    _createUserUiState.update {
                        BaseUiState.Success(result.data)
                    }
                }

                is AppResult.Error -> {
                    _createUserUiState.update {
                        BaseUiState.Error(result.error.toMessage())
                    }
                }
            }

        }
    }
}