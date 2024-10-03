package com.ita.myapp.classes.ui.screens


import android.R.attr.contentDescription
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem // Asegúrate de que este import está presente
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ComponentsScreen(navController: NavController) {
    var component by remember { mutableStateOf("") } // actualizar variable interfaz
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()

                //Content1
                NavigationDrawerItem(
                    label = {
                        Text(text = "Contenido1")
                    },
                    selected = false,
                    onClick = {
                        component = "Content1"
                        scope.launch {
                            drawerState.close() // Uso correcto para cerrar el Drawer
                        }
                    }
                )//end NavigationDrawerItem

                //Content2
                NavigationDrawerItem(
                    label = {
                        Text(text = "Contenido2")
                    },
                    selected = false,
                    onClick = {
                        component = "Content2"
                        scope.launch {
                            drawerState.close() // Uso correcto para cerrar el Drawer
                        }
                    }
                )//end NavigationDrawerItem

                //FloatingAction
                NavigationDrawerItem(
                    label = {
                        Text(text = "Floating Buttons")
                    },
                    selected = false,
                    onClick = {
                        component = "floating-buttons"
                        scope.launch {
                            drawerState.close() // Uso correcto para cerrar el Drawer
                        }
                    }
                )//end NavigationDrawerItem

                //Chips
                NavigationDrawerItem(
                    label = {
                        Text(text = "Chips")
                    },
                    selected = false,
                    onClick = {
                        component = "chips"
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )//end NavigationDrawerItem

            }//end ModelDrawerSheet
        }//drawerContent
    ) {

        Column {
            when(component){//switchcasse
                "Content1"->{
                    Content1()
                }
                "Content2"->{
                    Content2()
                }
                "floating-buttons"->{
                    FloatingButtons()
                }
                "chips"->{
                    FloatingButtons()
                }
            }
        }//end Column

    }
}

@Composable
fun Content1() {
    Text(text = "Content1")
}

@Composable
fun Content2() {
    Text(text = "Content2")
}
@Composable
fun Buttons(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        Button(
            onClick = {}
        ){
            Text(text = "Filled")
        }

        FilledTonalButton(
            onClick = {}
        ){
            Text(text = "Tonal")
        }

        OutlinedButton(
            onClick = {}
        ){
            Text(text = "Outlined")
        }

        ElevatedButton(
            onClick = {}
        ){
            Text(text = "Elevated")
        }

        TextButton(
            onClick = {}
        ){
            Text(text = "Text")
        }

    }
}
//@Preview(showBackground = true)
@Composable
fun FloatingButtons( ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }

        SmallFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }

        LargeFloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }
        ExtendedFloatingActionButton(
            onClick = {},
            icon =  {Icon(Icons.Filled.Add, "")},
            text = {Text(text = "Extended FAB")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chips( ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        AssistChip(
            onClick = {},
            label = { Text(text = "Assist Chip")},
            leadingIcon = {
                Icon(Icons.Filled.AccountBox, "",
                    Modifier.size(AssistChipDefaults.IconSize))
            }
        )//end AssistChip

        var selected by remember { mutableStateOf(false)}

        FilterChip(

            selected = selected,
            onClick = {},
            label = { Text(text = "Filter Chip")},
            leadingIcon = if(selected){
                {
                    Icon(
                        Icons.Filled.AccountBox, "",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )//end Icon
                }
            }else{
                null
            }

        )
        InputChipExample(text = "Dismiss",{})

    }
}//end Chips

@Composable
fun InputChipExample(
    text: String,
    onDismiss: () -> Unit
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return

    InputChip(
        label = { Text(text) },
        selected = enabled, // Cambié 'select' a 'selected'
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        avatar = {
            Icon(
                imageVector = Icons.Filled.Person, // Envolví el ícono en `Icon`
                contentDescription = null,
                modifier = Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Person, // Envolví el ícono en `Icon`
                contentDescription = null,
                modifier = Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun Progress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun Sliders() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        var sliderPosition by remember {mutableStateOf(value = 50f)}
       Column {
           Slider(
               value = sliderPosition,
               onValueChange = { sliderPosition = it},
               steps = 10,
               valueRange = 0f..100f

           )
           Text(
               textAlign = TextAlign.Center,
               modifier = Modifier.fillMaxWidth(),
               text = sliderPosition.toString()
           )

       }

    }
}

@Preview(showBackground = true)
@Composable
fun Switches() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        var checked by remember {mutableStateOf(true)}
        Switch(
            checked = checked,
            onCheckedChange={
                checked = it
            }

        )

        var checked2 by remember { mutableStateOf(true) }
        Switch(
            checked = checked2,
            onCheckedChange = { checked2 = it }, // Cambié `checked` por `checked2`
            // Removido `thumbContent` porque no es soportado en Switch
        )

        var checked3 by remember { mutableStateOf(true) }
        Checkbox(
            checked = checked3,
            onCheckedChange = { checked3 = it }
        )

    }
}
@Composable
fun Badges() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var itemCount by remember { mutableStateOf(0) }
        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White

                    ){
                        Text("$itemCount")
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "h"
            )
        }
        Button(
            onClick = { itemCount++ }
        ){
            Text("Add item")
        }

    }
}

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickers() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("DOB") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
                }
            }
        }
    }

}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickers(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ){
        val currentTime = Calendar.getInstance()

        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = true,
        )

        TimePickerDialog(
            onDismiss = { onDismiss() },
            onConfirm = { onConfirm(timePickerState) }
        ) {
            TimePicker(
                state = timePickerState,
            )
        }
    }

}

@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("OK")
            }
        },
        text = { content() }
    )
}