package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.common.AppResult
import com.androidapp.myportfolioappandroid.core.common.toMessage
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.AddUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.CreateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.DeleteUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.UpdateUser
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.AddUserUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.DeleteUserUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.GetUserListUseCase
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserApiViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {
    private val _userList = MutableStateFlow<BaseUiState<List<User>>?>(null)
    val userList: StateFlow<BaseUiState<List<User>>?> = _userList.asStateFlow()

    private val _createUserUiState = MutableStateFlow<BaseUiState<CreateUser>?>(null)
    val uiState: StateFlow<BaseUiState<CreateUser>?> = _createUserUiState.asStateFlow()

    private val _updateUserUiState = MutableStateFlow<BaseUiState<UpdateUser>?>(null)
    val updateUserUiState: StateFlow<BaseUiState<UpdateUser>?> = _updateUserUiState.asStateFlow()

    private val _deleteUserUiState = MutableStateFlow<BaseUiState<DeleteUser>?>(null)
    val deleteUserUiState: StateFlow<BaseUiState<DeleteUser>?> = _deleteUserUiState.asStateFlow()

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

    fun updateUser(
        user: User
    ) {
        val body = User(
            id = user.id,
            name = user.name,
            email = user.email
        )

        viewModelScope.launch {
            _updateUserUiState.update {
                BaseUiState.Loading
            }

            when (val result = updateUserUseCase(body)) {
                is AppResult.Success -> {
                    _updateUserUiState.update {
                        BaseUiState.Success(result.data)
                    }
                }

                is AppResult.Error -> {
                    _updateUserUiState.update {
                        BaseUiState.Error(result.error.toMessage())
                    }
                }
            }
        }

    }

    fun deleteUser(
        id: Int
    ) {
        viewModelScope.launch {
            _deleteUserUiState.update {
                BaseUiState.Loading
            }

            when (val result = deleteUserUseCase(id)) {
                is AppResult.Success -> {
                    _deleteUserUiState.update {
                        BaseUiState.Success(result.data)
                    }
                }

                is AppResult.Error -> {
                    _deleteUserUiState.update {
                        BaseUiState.Error(result.error.toMessage())
                    }
                }
            }
        }
    }
}
