package com.ita.myapp.classes.ui.screens

import android.R.attr.id
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Menu
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import com.ita.myapp.classes.data.model.MenuModel
import kotlinx.coroutines.launch
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APisScreen(navController: NavController) {
    val menuOptions = listOf(
        MenuModel(1, "Tareas en segundo plano", "BackgroundTasks", Icons.Filled.Schedule),
        MenuModel(2, "Rastreo y geolocalización", "LocationTracking", Icons.Filled.LocationOn),
        MenuModel(3, "Contactos y calendario", "ContactsCalendar", Icons.Filled.CalendarToday),
        MenuModel(4, "Sensores biométricos", "BiometricSensors", Icons.Filled.Fingerprint),
        MenuModel(5, "Cámara y archivos", "CameraFiles", Icons.Filled.PhotoCamera),
        MenuModel(6, "WIFI y datos celulares", "WifiCellularData", Icons.Filled.Wifi)
    )

    var selectedOption by rememberSaveable { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("APIs Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                LazyColumn {
                    items(menuOptions) { item ->
                        NavigationDrawerItem(
                            icon = { Icon(Icons.Default.Menu, contentDescription = null) }, // Icono genérico
                            label = { Text(item.title) },
                            selected = selectedOption == item.option,
                            onClick = {
                                selectedOption = item.option
                                scope.launch { drawerState.close() }
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when (selectedOption) {
                "BackgroundTasks" -> BackgroundTasksContent()
                "LocationTracking" -> LocationTrackingContent()
                "ContactsCalendar" -> ContactsCalendarContent()
                "BiometricSensors" -> BiometricSensorsContent()
                "CameraFiles" -> CameraFilesContent()
                "WifiCellularData" -> WifiCellularDataContent()
                else -> Text("Seleccione una opción del menú")
            }
        }
    }
}

@Composable
fun BackgroundTasksContent() {
    val context = LocalContext.current
    var isWorkScheduled by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0) }
    var isWorkComplete by remember { mutableStateOf(false) }

    val workManager = WorkManager.getInstance(context)
    val workRequest = remember { OneTimeWorkRequestBuilder<BackgroundTaskWorker>().build() }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Contenido para Tareas en segundo plano")

        Button(onClick = {
            // Programar el trabajo en segundo plano
            workManager.enqueue(workRequest)
            isWorkScheduled = true
            isWorkComplete = false
        }) {
            Text("Iniciar tarea en segundo plano")
        }

        if (isWorkScheduled) {
            // Observar el progreso del Worker
            LaunchedEffect(Unit) {
                workManager.getWorkInfoByIdLiveData(workRequest.id).observeForever { workInfo ->
                    workInfo?.let {
                        if (it.progress.keyValueMap.containsKey("progress")) {
                            progress = it.progress.getInt("progress", 0)
                        }
                        if (it.state.isFinished) {
                            isWorkComplete = true
                            isWorkScheduled = false
                        }
                    }
                }
            }

            // Mostrar el progreso en pantalla
            if (!isWorkComplete) {
                Text("Tarea en progreso: Paso $progress de 5")
            } else {
                Text("Tarea completada con éxito.")
            }
        }
    }
}


@Composable
fun LocationTrackingContent() {
    Text("Contenido para Rastreo y geolocalización")
}

@Composable
fun ContactsCalendarContent() {
    Text("Contenido para Contactos y calendario")
}

@Composable
fun BiometricSensorsContent() {
    Text("Contenido para Sensores biométricos")
}

@Composable
fun CameraFilesContent() {
    Text("Contenido para Cámara y archivos")
}

@Composable
fun WifiCellularDataContent() {
    Text("Contenido para WIFI y datos celulares")
}