package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.core.util.ValidationUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component.ItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserApiScreen(
    modifier: Modifier = Modifier,
    viewModel: UserApiViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val userListUiState by viewModel.userList.collectAsStateWithLifecycle()
    val createUserUiState by viewModel.uiState.collectAsStateWithLifecycle()

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    var nameError by rememberSaveable { mutableStateOf<String?>(null) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }

    var isShowAddUser by rememberSaveable { mutableStateOf(false) }

    fun onAddUser() {
        nameError = ValidationUtil.validateName(name)
        emailError = ValidationUtil.validateEmail(email)

        if (nameError != null || emailError != null) {
            return
        }

        viewModel.addUser(
            name = name.trim(),
            email = email.trim()
        )
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
        when (val state = userListUiState) {
            is BaseUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    items(
                        count = state.data.size,
                        key = { index ->
                            index
                        }
                    ) {
                        val user = state.data[it]
                        ItemCard(
                            name = user.name,
                            email = user.email,
                            onMoreVertClick = {}
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
                                    onAddUser()
                                },
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text("Add")
                            }
                        }
                    }
                }
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserApiScreenPreview() {
    MyPortfolioAppAndroidTheme() {
        UserApiScreen(
            onBack = {}
        )
    }
}
