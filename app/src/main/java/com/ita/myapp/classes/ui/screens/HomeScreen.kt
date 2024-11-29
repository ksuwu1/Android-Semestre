package com.ita.myapp.classes.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.ita.myapp.classes.R
import com.ita.myapp.classes.data.model.controller.ServiceViewModel
import com.ita.myapp.classes.data.model.ServiceEntity
import com.ita.myapp.classes.data.model.ServiceModel
import com.ita.myapp.classes.ui.components.ServiceCard
import com.ita.myapp.classes.ui.components.ServiceDetailCard
import com.ita.myapp.classes.ui.components.TopBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ita.myapp.classes.data.model.database.AppDatabase
import com.ita.myapp.classes.data.model.database.DatabaseProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (navController: NavController, viewModel: ServiceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)

    var serviceDetail by remember { mutableStateOf<ServiceModel?>(null) }
    var sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var showBottomSheet by remember { mutableStateOf(false) }

    var services by remember {mutableStateOf<List<ServiceEntity>>(emptyList())}
    val serviceDao = db.serviceDao()

    Scaffold(
        topBar = { TopBar("Password Manager", navController, false) },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorResource(R.color.purple_500),
                contentColor = Color.Black,
                onClick = {
                    navController.navigate("manage-service/0")
                }) {
                Icon(Icons.Default.Add, contentDescription = "Add icon")
            }
        }
    ){ innerPadding ->

        LaunchedEffect(Unit) {
            services =  withContext(Dispatchers.IO) {
                viewModel.getServices(db)
                serviceDao.getAll()
            }
        }

        val listState = rememberLazyListState()
        LazyColumn (
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(R.color.black))
                .fillMaxSize(),
            state = listState
        ){
            Log.d("debuginfo",services.toString())
            items(services){ service ->
                ServiceCard(service.id, service.name , service.username, service.imageURL,
                    onButtonClick = {
                        viewModel.showService(service.id){ response ->
                            if(response.isSuccessful){
                                serviceDetail = response.body()
                            }
                        }
                        showBottomSheet=true
                    }
                )
            }
        }
        if(showBottomSheet){
            ModalBottomSheet(
                containerColor = colorResource(id = R.color.purple_200),
                contentColor = Color.White,
                modifier = Modifier.fillMaxHeight(),
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                ServiceDetailCard(
                    id = serviceDetail?.id ?: 0,
                    name = serviceDetail?.name ?: "",
                    username = serviceDetail?.username?:"",
                    password = serviceDetail?.password?:"",
                    description = serviceDetail?.description?:"",
                    imageURL = serviceDetail?.imageURL.toString(),
                    onEditClick = {
                        showBottomSheet = false
                        navController.navigate("manage-service/" + serviceDetail?.id)
                    }
                )
            }
        }
    }
}