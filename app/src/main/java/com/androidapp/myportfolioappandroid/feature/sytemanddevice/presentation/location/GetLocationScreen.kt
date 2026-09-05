package com.androidapp.myportfolioappandroid.feature.sytemanddevice.presentation.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.North
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidapp.myportfolioappandroid.R
import com.androidapp.myportfolioappandroid.core.ui.component.FeatureScaffold
import com.androidapp.myportfolioappandroid.core.ui.theme.AppSpacing
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetLocationScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: GetLocationViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val getLocationUiState by viewModel.getLocationUiState.collectAsStateWithLifecycle()

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    @SuppressLint("MissingPermission")
    fun getCurrentLocations() {
        fusedLocationClient
            .getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            )
            .addOnSuccessListener { location ->
                if (location != null) {
                    viewModel.onLocationResult(
                        location.latitude,
                        location.longitude
                    )

                    println("=====> Location:  ${getLocationUiState.latitude}, ${getLocationUiState.longitude}")

                    Log.d(
                        "Location",
                        "Latitude: ${location.latitude}, " +
                                "Longitude: ${location.longitude}"
                    )
                } else {
                    Toast.makeText(
                        context,
                        "Unable to get location",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                Log.e(
                    "Location",
                    "Failed to get location",
                    e
                )
            }
    }

    fun checkLocationPermission() {
        if (locationPermissionState.allPermissionsGranted) {
            getCurrentLocations()
        } else {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    FeatureScaffold(
        modifier = modifier,
        title = "Location",
        onBackClick = onBack,
        floatingActionButton = {
            Button(
                onClick = {
                    checkLocationPermission()
                }
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.im_location),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.padding(horizontal = AppSpacing.extraSmall))

                    Text(
                        text = "Share precise location"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(AppSpacing.large),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(AppSpacing.large),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = AppSpacing.extraExtraSmall)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = AppSpacing.largeMedium),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.North,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(AppSpacing.extraSmallMedium))
                        Text(
                            text = "Latitude",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(AppSpacing.extraExtraSmall))
                        Text(
                            text = getLocationUiState.latitude.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    VerticalDivider(
                        modifier = Modifier.height(AppSpacing.extraExtraLarge),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .padding(AppSpacing.medium)
                    ) {
                        Icon(
                            imageVector = Icons.Default.East,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(AppSpacing.extraSmallMedium))
                        Text(
                            text = "Longitude",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(AppSpacing.extraExtraSmall))
                        Text(
                            text = getLocationUiState.longitude.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppSpacing.large))

            Button(
                onClick = {
                    openGoogleMap(
                        context = context,
                        latitude = getLocationUiState.latitude ?: 0.0,
                        longitude = getLocationUiState.longitude ?: 0.0
                    )
                },
                shape = RoundedCornerShape(AppSpacing.extraMedium),
                contentPadding = PaddingValues(
                    horizontal = AppSpacing.large,
                    vertical = AppSpacing.extraMedium
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = null,
                    modifier = Modifier.size(AppSpacing.largeMedium)
                )
                Spacer(modifier = Modifier.width(AppSpacing.small))
                Text(text = "Open Google Map")
            }
        }
    }
}

fun openGoogleMap(
    context: Context,
    latitude: Double,
    longitude: Double,
    label: String = "Current Location"
) {
    val uri = "geo:$latitude,$longitude?q=$latitude,$longitude($label)".toUri()

    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val browserUri =
            "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude".toUri()

        context.startActivity(
            Intent(Intent.ACTION_VIEW, browserUri)
        )
    }
}





//@Composable
//fun GetLocationScreen(
//    modifier: Modifier = Modifier,
//    onBack: () -> Unit
//) {
//    val context = LocalContext.current
//    var locationText by remember { mutableStateOf("Click the button to get location") }
//
//    FeatureScaffold(
//        modifier = modifier,
//        title = "Location",
//        onBackClick = onBack,
//        floatingActionButton = {
//            Button(
//                onClick = {}
//            ) {
//                Row(
//                    modifier = Modifier,
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.im_location),
//                        contentDescription = null
//                    )
//
//                    Spacer(modifier = Modifier.padding(horizontal = AppSpacing.extraSmall))
//
//                    Text(
//                        text = "Share precise location"
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        // 1. Create the permission launcher
//        val locationPermissionLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.RequestMultiplePermissions()
//        ) { permissions ->
//            val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
//            val coarseLocationGranted =
//                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
//
//            if (fineLocationGranted || coarseLocationGranted) {
//                // Permission granted, fetch the location
//                fetchCurrentLocation(context) { location ->
//                    locationText = "Lat: ${location.latitude}, Long: ${location.longitude}"
//                }
//            } else {
//                locationText = "Permission Denied"
//            }
//        }
//
//        // 2. UI Layout
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = locationText)
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = {
//                if (hasLocationPermissions(context)) {
//                    fetchCurrentLocation(context) { location ->
//                        locationText = "Lat: ${location.latitude}, Long: ${location.longitude}"
//                    }
//                } else {
//                    // Request permissions if not already granted
//                    locationPermissionLauncher.launch(
//                        arrayOf(
//                            Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION
//                        )
//                    )
//                }
//            }) {
//                Text("Get My Location")
//            }
//        }
//    }
//}
//
//// Helper function to check if permissions are already given
//private fun hasLocationPermissions(context: Context): Boolean {
//    return ContextCompat.checkSelfPermission(
//        context, Manifest.permission.ACCESS_FINE_LOCATION
//    ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
//        context, Manifest.permission.ACCESS_COARSE_LOCATION
//    ) == PackageManager.PERMISSION_GRANTED
//}
//
//// Core function fetching the snapshot location safely
//@SuppressLint("MissingPermission")
//private fun fetchCurrentLocation(context: Context, onLocationReceived: (Location) -> Unit) {
//    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//
//    // Request a highly accurate one-time location snapshot
//    fusedLocationClient.getCurrentLocation(
//        Priority.PRIORITY_HIGH_ACCURACY,
//        CancellationTokenSource().token
//    ).addOnSuccessListener { location: Location? ->
//        if (location != null) {
//            onLocationReceived(location)
//        } else {
//            // Fallback to last known location if current is temporarily null
//            fusedLocationClient.lastLocation.addOnSuccessListener { lastKnown ->
//                lastKnown?.let(onLocationReceived)
//            }
//        }
//    }
//}
