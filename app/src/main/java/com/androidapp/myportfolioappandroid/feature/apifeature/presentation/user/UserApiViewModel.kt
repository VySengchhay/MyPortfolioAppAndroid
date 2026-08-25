package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.feature.apifeature.data.remote.dto.response.UserListResponse
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.User
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.usecase.user.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserApiViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {
    private val _userList = MutableStateFlow<BaseUiState<List<User>>?>(null)
    val userList: StateFlow<BaseUiState<List<User>>?> = _userList.asStateFlow()

    init {
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {
            getUserListUseCase().collect {
                _userList.emit(it)
            }
        }
    }
}