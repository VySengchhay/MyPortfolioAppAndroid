package com.androidapp.myportfolioappandroid.feature.auth.presentation.emailverification

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.androidapp.myportfolioappandroid.core.ui.theme.MyPortfolioAppAndroidTheme
import kotlinx.coroutines.delay

private const val POLL_INTERVAL_MS = 4_000L

private fun remainingSecondsUntil(deadlineMillis: Long?): Int {
    val deadline = deadlineMillis ?: return (VERIFICATION_WINDOW_MS / 1_000L).toInt()
    return ((deadline - System.currentTimeMillis()) / 1_000L).toInt().coerceAtLeast(0)
}

@Composable
fun EmailVerificationDialog(
    viewModel: EmailVerificationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var remainingSeconds by remember { mutableIntStateOf(remainingSecondsUntil(uiState.deadlineMillis)) }

    // Check as soon as the user returns from their email app instead of waiting for the next poll.
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.checkVerification()
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(POLL_INTERVAL_MS)
            viewModel.checkVerification()
        }
    }

    // Deadline is anchored to the account's real creation time (Firebase-persisted),
    // so this stays correct even if the app is killed and reopened mid-wait.
    LaunchedEffect(uiState.deadlineMillis) {
        while (!uiState.isVerified && !uiState.isCancelling) {
            remainingSeconds = remainingSecondsUntil(uiState.deadlineMillis)
            if (remainingSeconds <= 0) {
                viewModel.cancel(dueToTimeout = true)
                break
            }
            delay(1_000)
        }
    }

    // Verification and cancellation are handled by the caller: both change the signed-in user,
    // which removes this dialog from composition before its own effects could react.

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        BackHandler(enabled = !uiState.isCancelling) {
            viewModel.cancel()
        }

        EmailVerificationDialogContent(
            email = uiState.email,
            remainingSeconds = remainingSeconds,
            isCancelling = uiState.isCancelling
        )
    }
}

@Composable
private fun EmailVerificationDialogContent(
    email: String,
    remainingSeconds: Int,
    isCancelling: Boolean
) {
    Card(
        shape = RoundedCornerShape(AppSpacing.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = AppSpacing.small)
    ) {
        Column(
            modifier = Modifier.padding(AppSpacing.extraLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(40.dp)
            )

            Spacer(Modifier.height(AppSpacing.large))

            Text(
                text = if (isCancelling) {
                    "Cancelling registration"
                } else {
                    "Waiting for you to verify your email"
                },
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(AppSpacing.extraSmall))

            Text(
                text = if (isCancelling) {
                    "Please wait a moment..."
                } else {
                    "We sent a link to $email. Tap it, then this screen will continue automatically."
                },
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (!isCancelling) {
                Spacer(Modifier.height(AppSpacing.medium))

                Text(
                    text = "This will cancel automatically in ${remainingSeconds}s",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmailVerificationDialogContentPreview() {
    MyPortfolioAppAndroidTheme {
        EmailVerificationDialogContent(email = "jane.doe@example.com", remainingSeconds = 42, isCancelling = false)
    }
}
