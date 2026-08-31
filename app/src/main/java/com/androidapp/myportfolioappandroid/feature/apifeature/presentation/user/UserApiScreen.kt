package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.core.util.ValidationUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.domain.model.user.User
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component.ItemCard
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user.component.AppSearchBar
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component.DropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserApiScreen(
    modifier: Modifier = Modifier,
    viewModel: UserApiViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    val userListUiState by viewModel.userList.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val createUserUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val updateUserUiState by viewModel.updateUserUiState.collectAsStateWithLifecycle()
    val deleteUserUiState by viewModel.deleteUserUiState.collectAsStateWithLifecycle()

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    var nameError by rememberSaveable { mutableStateOf<String?>(null) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }

    var isShowAddUser by rememberSaveable { mutableStateOf(false) }

    var expendedIndex by rememberSaveable { mutableIntStateOf(-1) }

    var id by rememberSaveable { mutableStateOf(0) }
    var isEdit by rememberSaveable { mutableStateOf(false) }

    fun onAddUser() {
        nameError = ValidationUtil.validateName(name)
        emailError = ValidationUtil.validateEmail(email)

        if (nameError != null || emailError != null) {
            return
        }

        isEdit = false

        viewModel.addUser(
            name = name.trim(),
            email = email.trim()
        )
    }

    fun onUpdateUser() {
        nameError = ValidationUtil.validateName(name)
        emailError = ValidationUtil.validateEmail(email)

        if (nameError != null || emailError != null) {
            return
        }

        val user = User(
            id = id,
            name = name.trim(),
            email = email.trim()
        )

        viewModel.updateUser(user)
    }

    fun onEdit(user: User) {
        id = user.id
        name = user.name
        email = user.email
        isEdit = true
        isShowAddUser = true
    }

    fun onRemoveUser(id: Int) {
        viewModel.deleteUser(id)
    }

    fun onToastMessage(
        message: String
    ) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onClearForm() {
        name = ""
        email = ""
        nameError = null
        emailError = null
    }

    LaunchedEffect(userListUiState) {
        when (val state = userListUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    LaunchedEffect(createUserUiState) {
        when (val state = createUserUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                viewModel.getUserList()
                isShowAddUser = false
                onClearForm()
                onToastMessage(state.data.message)
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    LaunchedEffect(updateUserUiState) {
        when (val state = updateUserUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                viewModel.getUserList()
                isShowAddUser = false
                isEdit = false
                onClearForm()
                onToastMessage(state.data.message)
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    LaunchedEffect(deleteUserUiState) {
        when (val state = deleteUserUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                viewModel.getUserList()
                onToastMessage(state.data.message)
            }

            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.message)
            }

            is BaseUiState.ErrorWithException -> {
                LoadingUtil.hideLoading()
                onToastMessage(state.exception.message ?: "Unknown error")
            }

            else -> {}
        }
    }

    FeatureScaffold(
        modifier = modifier,
        title = "CRUD User",
        onBackClick = onBack,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isShowAddUser = true
                },
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    ),
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add User"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
        ) {
            AppSearchBar(
                query = searchQuery,
                onQueryChange = viewModel::searchQueryChange,
            )
            when (val state = userListUiState) {
                is BaseUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                    ) {
                        items(
                            count = state.data.size,
                            key = { index ->
                                index
                            }
                        ) { index ->
                            val user = state.data[index]
                            ItemCard(
                                item = user,
                                onClick = { user ->
                                    onEdit(user)
                                },
                                trailingIcon = {
                                    Box(
                                        modifier = Modifier
                                    ) {
                                        IconButton(
                                            onClick = {
                                                expendedIndex = if (expendedIndex == index) {
                                                    -1
                                                } else {
                                                    index
                                                }
                                            }
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_more_vert),
                                                contentDescription = "More"
                                            )
                                        }

                                        if (!isShowAddUser) {
                                            DropdownMenu(
                                                expanded = expendedIndex == index,
                                                onExpandedChange = { expanded ->
                                                    expendedIndex = if (expanded) index else -1
                                                },
                                                onEditClick = {
                                                    onEdit(user)
                                                },
                                                onRemoveClick = {
                                                    onRemoveUser(user.id)
                                                }
                                            )
                                        }
                                    }
                                }
                            )
                            HorizontalDivider()
                        }
                    }

                    if (isShowAddUser) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                isShowAddUser = false
                                onClearForm()
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(AppSpacing.medium)
                            ) {
                                Text(
                                    text = "Add User",
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Spacer(Modifier.padding(AppSpacing.extraSmall))

                                OutlinedTextField(
                                    value = name,
                                    onValueChange = { value ->
                                        name = value
                                        if (nameError != null) {
                                            nameError = ValidationUtil.validateName(value)
                                        }
                                    },
                                    label = { Text("Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = nameError != null,
                                    supportingText = {
                                        nameError?.let { errorMessage ->
                                            Text(
                                                text = errorMessage
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.padding(AppSpacing.extraSmall))

                                OutlinedTextField(
                                    value = email,
                                    onValueChange = { value ->
                                        email = value
                                        if (emailError != null) {
                                            emailError = ValidationUtil.validateEmail(value)
                                        }
                                    },
                                    label = { Text("Email") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = emailError != null,
                                    supportingText = {
                                        emailError?.let { errorMessage ->
                                            Text(
                                                text = errorMessage
                                            )
                                        }
                                    }
                                )

                                Spacer(Modifier.padding(AppSpacing.extraSmall))

                                Button(
                                    onClick = {
                                        if (isEdit) {
                                            onUpdateUser()
                                        } else {
                                            onAddUser()
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    if (isEdit) {
                                        Text("Update")
                                    } else {
                                        Text("Add")
                                    }
                                }
                            }
                        }
                    }
                }



                else -> {}
            }


        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun UserApiScreenPreview() {
//    MyPortfolioAppAndroidTheme() {
//        UserApiScreen(
//            onBack = {}
//        )
//    }
//}
