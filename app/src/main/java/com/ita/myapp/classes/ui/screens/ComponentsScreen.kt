package com.ita.myapp.classes.ui.screens

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
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
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Components(navController: NavController) {
    var component by remember{ mutableStateOf("") } //Can assign a value
    // A reactive component to UI COMPONENTS
    // A global variable that its state can by updated using buttons

    var drawerState = rememberDrawerState(initialValue= DrawerValue.Closed)
    val scope = rememberCoroutineScope() //Update drawer state, is it closed?
    ModalNavigationDrawer( //Screen that displays above our content
        drawerState=drawerState, //Current state of drawer
        // drawer content
        drawerContent = { //Content of menu
            ModalDrawerSheet {
                Text("Menu",
                    modifier = Modifier
                        .padding(16.dp))
                HorizontalDivider() // Line

                //Show content 1
                NavigationDrawerItem(label = { Text("Content 1") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Content1"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )
                // Show content 2
                NavigationDrawerItem(label = { Text("Content 2") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Content2"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                //Buttons
                NavigationDrawerItem(label = { Text("Buttons") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Buttons"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                //Floating
                NavigationDrawerItem(label = { Text("Floating Buttons") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Floating"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                // Chips
                NavigationDrawerItem(label = { Text("Chips") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Chips"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )


                //Progress()
                NavigationDrawerItem(label = { Text("Progress") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Progress"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )
                //Sliders()
                NavigationDrawerItem(label = { Text("Sliders") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Sliders"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )
                //Switches()
                NavigationDrawerItem(label = { Text("Switches") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Switches"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                //Badges
                NavigationDrawerItem(label = { Text("Badges") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Badges"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )


                //TimePickers
                NavigationDrawerItem(label = { Text("TimePickers") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="TimePickers"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                //DatePickers
                NavigationDrawerItem(label = { Text("DatePickers") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="DatePickers"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )
            }

        }) {
        Column{
            when(component){
                "Content1" -> {
                    Content1()
                }
                "Content2" ->{
                    Content2()
                }
                "Buttons" ->{
                    Buttons()
                }
                "Floating" ->{
                    FloatingButtons()
                }
                "Chips" ->{
                    Chips()
                }
                "Progress"->{
                    Progress()
                }
                "Sliders"->{
                    Sliders()
                }
                "Switches"->{
                    Switches()
                }
                "Badges"->{
                    Badges()
                }
                "TimePickers" ->{
                    ShowTimePicker()
                }
                "DatePickers"->{
                    DatePickerDocked()
                }

            }
            /*Text(text = component)
            Text(text="This is the Components")
            Button(onClick = { navController.navigate("menu")}) {
            }
            Content1()
            Content2()*/
        }

    }


}

@Preview(showBackground = true)
@Composable
fun Content1(){
    Text(text="Content 1")
}

@Preview(showBackground = true)
@Composable
fun Content2(){
    Text(text="Content 2")
}

//@Preview(showBackground = true)
@Composable
fun Buttons(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Button(onClick = {}){
            Text("Filled")
        }
        FilledTonalButton(onClick = {}){ //Button with lower color
            Text("Tonal")
        }
        OutlinedButton(onClick = {}){ // Only border
            Text("Outlined")
        }
        ElevatedButton(onClick = {}) { //Buton with shadow
            Text("Elevated")
        }
        TextButton(onClick = { }) { //Hyperlink
            Text("Text")
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun FloatingButtons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Filled.Add,"")
        }
        SmallFloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add,"")
        }
        LargeFloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add,"")
        }
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            icon = {Icon(Icons.Filled.Add,"")},
            text = {Text("Extended")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chips() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AssistChip(
            onClick = { /*TODO*/ },
            label = { Text("Assist Chip") },
            leadingIcon={Icon(Icons.Filled.Add,"",
                modifier= Modifier.size(AssistChipDefaults.IconSize) // Stays resizeable according to chip
            )
            }
        )

        var selected by remember { mutableStateOf(false) } // It can change the interface, initial value is false
        FilterChip(
            selected = selected,
            onClick = { selected = !selected},
            label = { Text("Toggle") },
            leadingIcon= {
                if(selected){
                    Icon(Icons.Filled.Add,"")
                }else{
                    null
                }
            }
        )
        InputChipExample(text = "Dismiss",{})

    }
}


@Composable
fun InputChipExample(
    text : String,
    onDismiss: () -> Unit
){
    var enabled by remember {mutableStateOf(true)} // true as default value
    if(!enabled) return // Returns nothing

    InputChip(
        label = { Text(text) },
        selected = enabled,
        onClick = {
            onDismiss()
            enabled = ! enabled
        },
        avatar = { //Icon
            Icon(
                Icons.Filled.Person,
                contentDescription = "",
                modifier=Modifier.size(InputChipDefaults.AvatarSize),
            )
        },
        trailingIcon = { //End Icon
            Icon(
                Icons.Filled.Close,
                contentDescription = "",
                modifier=Modifier.size(InputChipDefaults.AvatarSize),
            )
        }
    )
}




@Preview(showBackground = true)
@Composable
fun Progress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
        ) // Progress line
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
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Float 50
        var sliderPosition by remember{ mutableStateOf(50f) }

        Column{
            Slider(
                value = sliderPosition,
                onValueChange = {sliderPosition = it}, //Updates sliderPosition value
                steps= 10, //Increments when moving the slider
                valueRange = 0f..100f //Min and Max values

            )
            Text(
                text=sliderPosition.toString(),
                modifier=Modifier.fillMaxWidth(),
                textAlign= TextAlign.Center,
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Switches() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var checked by remember{mutableStateOf(true)}
        Switch(
            checked=checked,
            onCheckedChange={
                checked = it
            }
        )

        var checked2 by remember{mutableStateOf(true)}
        Switch(
            checked=checked2,
            onCheckedChange={
                checked2 = it
            },
            thumbContent = if (checked2){
                {Icon(
                    Icons.Filled.Check,
                    contentDescription = "",
                    modifier=Modifier.size(InputChipDefaults.AvatarSize),
                )}
            }
            else{null}
        )

        var checked3 by remember{mutableStateOf(true)}
        Checkbox(checked = checked3, onCheckedChange = {checked3 = it})
    }
}


@Preview(showBackground = true)
@Composable
fun Badges() { // Alerts in some icons or sections
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var itemCount by remember{mutableStateOf(0)}

        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red, // Background
                        contentColor = Color.White// Text
                    ){
                        Text("$itemCount")
                    }
                }
            }
        ) {
            Icon(Icons.Filled.ShoppingCart,"")
        }
        Button(
            onClick = {itemCount++}
        ){
            Text("Add Item")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DatePickers1() { // Alerts in some icons or sections
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        DatePickerDocked()
        //DatePickerModal(onDateSelected = {}, onDismiss = {})
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

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

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun ShowTimePicker(){
    TimePickers1(onDismiss = {}, onConfirm = {})
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun TimePickers1(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) { // Alerts in some icons or sections
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val currentTime = Calendar.getInstance()

        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = true,
        )

        Column {
            TimePicker(
                state = timePickerState,
            )
            Button(onClick = onDismiss) {
                Text("Dismiss picker")
            }
            Button(onClick = onConfirm) {
                Text("Confirm selection")
            }
        }
    }
}