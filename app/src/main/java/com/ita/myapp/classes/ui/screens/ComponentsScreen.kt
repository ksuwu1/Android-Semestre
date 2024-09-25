package com.ita.myapp.classes.ui.screens

import android.R.attr.label
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select


@Composable
fun HomeScreen(navController: NavController){
    var component by remember() { mutableStateOf("") }
    val drawerState = rememberDrawerState(InitialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


        ModalNavigationDrawer(
            drawerState= drawerState,
            drawerContent = {
                ModalDrawerSheet {
                 Text(text = "Menu", Modifier = Modifier.padding(16.dp))
                    HorizontalDivider()
                    NavigationDrawerItem(
                    label={ Text(text = "Content 1")},
                    selected = false,
                    onClick = {
                    component = "Content1"
                        scope.launch{
                            drawerState.apply {
                                drawerState.apply {
                                    close()
                                }
                            }

                        }

                    }
            )
                    NavigationDrawerItem(
                        label={ Text(text = "Content 2")},
                        selected = false,
                        onClick = {
                            component = "Content2"
                            scope.launch{
                                drawerState.apply {
                                    drawerState.apply {
                                        close()
                                    }
                                }

                            }

                        }
                    )

                }
            }

        ) {
            Column{
                when(component){
                    "Content1" -> {
                        Content1()

                    }
                    "Content2" -> {
                        Content2()



                }
                }


        }
        }
        }


@Composable
fun Content1(){
  Text(text = "Content 1")
}

@Composable
fun Content2(){
    Text(text = "Content 2")
}
