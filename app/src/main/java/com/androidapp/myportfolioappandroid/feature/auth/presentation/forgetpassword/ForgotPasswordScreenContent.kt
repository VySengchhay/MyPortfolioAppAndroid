package com.androidapp.myportfolioappandroid.feature.auth.presentation.forgetpassword
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.feature.auth.presentation.component.AuthHeader
import com.androidapp.myportfolioappandroid.feature.auth.presentation.component.AuthPrimaryButton
import com.androidapp.myportfolioappandroid.feature.auth.presentation.component.AuthTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreenContent(
    uiState: ForgotPasswordUiState,
    onEmailChange: (String) -> Unit,
    onSendResetLinkClick: () -> Unit,
    onBackToLoginClick: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackToLoginClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to login",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(
                    horizontal = AppSpacing.screenHorizontal,
                    vertical = AppSpacing.screenVertical
                ),
            verticalArrangement = Arrangement.Center
        ) {
            AuthHeader(
                title = if (uiState.isEmailSent) "Check Your Email" else "Forgot Password?",
                subtitle = if (uiState.isEmailSent) {
                    "We've sent a password reset link to ${uiState.email}"
                } else {
                    "Enter your registered email and we'll send you a link to reset your password"
                },
                icon = if (uiState.isEmailSent) Icons.Filled.MarkEmailRead else Icons.Filled.Email,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppSpacing.extraLarge))

            if (!uiState.isEmailSent) {
                AuthTextField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(AppSpacing.large))

                AuthPrimaryButton(
                    text = "Send Reset Link",
                    onClick = onSendResetLinkClick,
                    isLoading = uiState.isLoading,
                    enabled = uiState.isFormValid
                )
            } else {
                AuthPrimaryButton(
                    text = "Back to Login",
                    onClick = onBackToLoginClick
                )

                Spacer(modifier = Modifier.height(AppSpacing.medium))

                TextButton(
                    onClick = onSendResetLinkClick,
                    enabled = uiState.canResend
                ) {
                    Text(
                        text = if (uiState.resendCooldownSeconds > 0) {
                            "Resend link in ${uiState.resendCooldownSeconds}s"
                        } else {
                            "Resend link"
                        }
                    )
                }
            }
        }
    }
}