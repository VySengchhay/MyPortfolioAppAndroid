package com.androidapp.myportfolioappandroid.feature.apifeature.presentation.user

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.state.BaseUiState
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import com.androidapp.myportfolioappandroid.core.util.LoadingUtil
import com.androidapp.myportfolioappandroid.feature.apifeature.presentation.component.ItemCard

@Composable
fun UserApiScreen(
    modifier: Modifier = Modifier,
    viewModel: UserApiViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    val userUiState by viewModel.userList.collectAsStateWithLifecycle()

    fun onToastMessage(
        message: String
    ) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    LaunchedEffect(userUiState) {
        when (val state = userUiState) {
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

    FeatureScaffold(
        modifier = modifier,
        title = "CRUD User",
        onBackClick = onBack,
    ) { innerPadding ->
        when (val state = userUiState) {
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
