package com.example.comeragallary.ui.components

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.comeragallary.utils.PermissionUtils


@Composable
fun MultiplePermissionsScreen(successCallback: () -> Unit) {
    val context = LocalContext.current

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
        android.Manifest.permission.READ_MEDIA_IMAGES,
        android.Manifest.permission.READ_MEDIA_VIDEO)
    } else listOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val permissionsState = remember { mutableStateMapOf<String, Boolean>() }
    val showRationale = remember { mutableStateOf(false) }
    val showSettingsDialog = remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        permissionsState.putAll(result)
        if (result.values.any { !it }) {
            val activity = context as Activity
            val show = permissions.any {
                !result[it]!! && ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
            }
            showRationale.value = show
            if(show.not()) {
                showSettingsDialog.value = true
            }
        } else {
            successCallback()
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(permissions.toTypedArray())
    }

    if (showRationale.value) {
        AlertDialog(
            onDismissRequest = { showRationale.value = false },
            title = { Text("Permissions Required") },
            text = { Text("These permissions are necessary for the app to function correctly.") },
            confirmButton = {
                TextButton(onClick = {
                    showRationale.value = false
                    launcher.launch(permissions.toTypedArray())
                }) {
                    Text("Grant Again")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRationale.value = false }) {
                    Text("Deny")
                }
            }
        )
    }
    if (showSettingsDialog.value) {
        AlertDialog(
            onDismissRequest = { showRationale.value = false },
            title = { Text("Permissions Required") },
            text = { Text("These permissions are necessary for the app to function correctly.") },
            confirmButton = {
                TextButton(onClick = {
                    showSettingsDialog.value = false
                    PermissionUtils.openAppSettings(context)
                }) {
                    Text("Settings")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSettingsDialog.value = false }) {
                    Text("Deny")
                }
            }
        )
    }

    // Use permissionsState to check if all granted
    if (permissions.all { permissionsState[it] == true }) {
        successCallback()
    } else {

    }
}

