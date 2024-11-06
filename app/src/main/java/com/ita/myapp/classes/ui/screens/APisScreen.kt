package com.ita.myapp.classes.ui.screens

import android.R.attr.id
import android.content.Context
import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ita.myapp.classes.ui.location.HomeView
import com.ita.myapp.classes.ui.location.SearchViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

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
    val searchVM: SearchViewModel = viewModel()

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
                "LocationTracking" -> LocationTrackingContent(navController, searchVM)
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
fun getCalendarId(context: Context): Long? {
    val projection = arrayOf(
        CalendarContract.Calendars._ID,
        CalendarContract.Calendars.ACCOUNT_TYPE
    )

    val cursor = context.contentResolver.query(
        CalendarContract.Calendars.CONTENT_URI,
        projection,
        null,
        null,
        null
    )

    cursor?.use {
        val idIndex = it.getColumnIndex(CalendarContract.Calendars._ID)
        val accountTypeIndex = it.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE)

        while (it.moveToNext()) {
            val accountType = if (accountTypeIndex != -1) {
                it.getString(accountTypeIndex)
            } else {
                null
            }
            if (accountType == CalendarContract.ACCOUNT_TYPE_LOCAL) {
                return if (idIndex != -1) {
                    it.getLong(idIndex)
                } else {
                    null
                }
            }
        }
    }
    Log.w("CalendarDebug", "No se encontró un calendario local.")
    return null
}
fun fetchContacts(context: Context): List<String> {
    val contacts = mutableListOf<String>()

    val cursor = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        null,
        null,
        null
    )
    cursor?.use {
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        while (it.moveToNext()) {
            val name = it.getString(nameIndex)
            contacts.add(name)
        }
    }

    return contacts
}


@Composable
fun LocationTrackingContent(navController: NavController, searchVM: SearchViewModel) {
    // Mostrar la vista de entrada de dirección
    HomeView(navController, searchVM)

    // Verificar si los datos son válidos antes de navegar
    OutlinedButton(onClick = {
        try {
            // Comprobar que las coordenadas no son nulas
            if (searchVM.lat != 0.0 && searchVM.long != 0.0 && searchVM.address.isNotEmpty()) {
                Log.d("APIScreen", "Lat: ${searchVM.lat}, Long: ${searchVM.long}, Address: ${searchVM.address}")
                // Realizar la navegación solo si los valores son válidos
                navController.navigate("MapsSearchView/${searchVM.lat}/${searchVM.long}/${searchVM.address}")
            } else {
                throw IllegalArgumentException("Coordenadas no válidas o dirección vacía")
            }
        } catch (e: Exception) {
            // Captura cualquier error y lo registra en el log
            Log.e("APIScreen", "Error al navegar o procesar la ubicación: ${e.message}")
            e.printStackTrace()  // Imprimir traza del error
            // Mostrar mensaje al usuario
            Toast.makeText(navController.context, "Error al procesar la ubicación: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }) {
        Text(text = "Enviar")
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsCalendarContent() {
    val context = LocalContext.current
    var selectedContact by remember { mutableStateOf("Selecciona un contacto") }
    var selectedDate by remember { mutableStateOf("Selecciona una fecha") }
    var selectedStartTime by remember { mutableStateOf("Selecciona hora de inicio") }
    var selectedEndTime by remember { mutableStateOf("Selecciona hora de fin") }
    var hasPermission by remember { mutableStateOf(false) }

    // Solicitar permisos al inicio
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasPermission = permissions[Manifest.permission.READ_CONTACTS] == true &&
                permissions[Manifest.permission.READ_CALENDAR] == true &&
                permissions[Manifest.permission.WRITE_CALENDAR] == true
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR
            )
        )
    }

    if (hasPermission) {
        // Si los permisos son otorgados, muestra la UI de selección de contacto y calendario
        var showContactDialog by remember { mutableStateOf(false) }
        val contacts = remember { fetchContacts(context) }

        Column(modifier = Modifier.padding(16.dp)) {
            // Botón para seleccionar el contacto
            Button(onClick = { showContactDialog = true }) {
                Text(text = selectedContact)
            }

            // Diálogo de selección de contactos
            if (showContactDialog) {
                AlertDialog(
                    onDismissRequest = { showContactDialog = false },
                    title = { Text("Selecciona un contacto") },
                    text = {
                        LazyColumn {
                            items(contacts) { contact ->
                                TextButton(onClick = {
                                    selectedContact = contact
                                    showContactDialog = false
                                }) {
                                    Text(text = contact)
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = { showContactDialog = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para seleccionar la fecha
            Button(onClick = {
                val currentDate = Calendar.getInstance()
                val datePickerDialog = android.app.DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        selectedDate = "$dayOfMonth/${month + 1}/$year"
                    },
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.show()
            }) {
                Text(text = selectedDate)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para seleccionar la hora de inicio
            Button(onClick = {
                val currentTime = Calendar.getInstance()
                val timePickerDialog = android.app.TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        selectedStartTime = String.format("%02d:%02d", hourOfDay, minute)
                    },
                    currentTime.get(Calendar.HOUR_OF_DAY),
                    currentTime.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            }) {
                Text(text = selectedStartTime)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para seleccionar la hora de fin
            Button(onClick = {
                val currentTime = Calendar.getInstance()
                val timePickerDialog = android.app.TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        selectedEndTime = String.format("%02d:%02d", hourOfDay, minute)
                    },
                    currentTime.get(Calendar.HOUR_OF_DAY),
                    currentTime.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.show()
            }) {
                Text(text = selectedEndTime)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar el evento en el calendario
            Button(onClick = {
                if (selectedContact != "Selecciona un contacto" &&
                    selectedDate != "Selecciona una fecha" &&
                    selectedStartTime != "Selecciona hora de inicio" &&
                    selectedEndTime != "Selecciona hora de fin") {

                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val dateInMillis = dateFormat.parse(selectedDate)?.time

                    if (dateInMillis != null) {
                        val (startHour, startMinute) = selectedStartTime.split(":").map { it.toInt() }
                        val (endHour, endMinute) = selectedEndTime.split(":").map { it.toInt() }

                        val startTimeInMillis = Calendar.getInstance().apply {
                            timeInMillis = dateInMillis
                            set(Calendar.HOUR_OF_DAY, startHour)
                            set(Calendar.MINUTE, startMinute)
                        }.timeInMillis

                        val endTimeInMillis = Calendar.getInstance().apply {
                            timeInMillis = dateInMillis
                            set(Calendar.HOUR_OF_DAY, endHour)
                            set(Calendar.MINUTE, endMinute)
                        }.timeInMillis

                        val calendarId = getCalendarId(context)
                        if (calendarId != null) {
                            saveEventToCalendar(context, selectedContact, startTimeInMillis, endTimeInMillis, calendarId)
                        } else {
                            Toast.makeText(context, "No se encontró un calendario local.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Error al convertir la fecha. Por favor, intenta de nuevo.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Por favor, selecciona un contacto, una fecha y las horas.", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Guardar Evento")
            }
        }
    } else {
        // Si los permisos no fueron otorgados, muestra un mensaje y un botón
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Se necesitan permisos para acceder a los contactos y el calendario.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.WRITE_CALENDAR
                    )
                )
            }) {
                Text(text = "Solicitar permisos")
            }
        }
    }
}

fun saveEventToCalendar(context: Context, contact: String, startTimeInMillis: Long, endTimeInMillis: Long, calendarId: Long) {
    val contentValues = ContentValues().apply {
        put(CalendarContract.Events.TITLE, "Evento con $contact")
        put(CalendarContract.Events.DTSTART, startTimeInMillis)
        put(CalendarContract.Events.DTEND, endTimeInMillis)
        put(CalendarContract.Events.CALENDAR_ID, calendarId)
        put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
    }

    try {
        val uri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues)
        if (uri != null) {
            Toast.makeText(context, "Evento guardado: $uri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error al guardar el evento", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
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