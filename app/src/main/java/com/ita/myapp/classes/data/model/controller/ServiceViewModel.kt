package com.ita.myapp.classes.data.model.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ita.myapp.classes.data.model.ServiceEntity
import kotlinx.coroutines.launch
import retrofit2.Response
import com.ita.myapp.classes.data.model.ServiceModel
import com.ita.myapp.classes.data.model.network.RetrofitClient
import com.ita.myapp.classes.data.model.toServiceEntity
import com.ita.myapp.classes.data.model.toServiceEntityList
import com.ita.myapp.classes.ui.screens.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ServiceViewModel: ViewModel() {
    val api = RetrofitClient.api

    fun getServices(db: AppDatabase){
        val serviceDao = db.serviceDao()
        viewModelScope.launch {
            try {
                val response =  api.getServices()
                if (response.body()?.count()!! > 0){
                    val serviceEntities = response.body()?.toServiceEntityList()
                    if (serviceEntities != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                serviceDao.insertAll(serviceEntities)
                            } catch (exception:Exception){
                                Log.d("error", exception.toString())
                            }
                        }
                    }
                }
            } catch (exception:Exception){
                print(exception)
            }
        }
    }

    fun showService(db: AppDatabase, id: Int, onResult: (ServiceEntity?) -> Unit) {
        val serviceDao = db.serviceDao()
        viewModelScope.launch {
            try {
                val response = api.getService(id)
                if (response.isSuccessful) {
                    val serviceModel = response.body()
                    if (serviceModel != null) {
                        val serviceEntity = serviceModel.toServiceEntity()
                        withContext(Dispatchers.IO) {
                            try {
                                serviceDao.insertAll(listOf(serviceEntity))
                            } catch (exception: Exception) {
                                Log.d("error", "Insert failed: ${exception.message}")
                            }
                        }
                    }
                }
                // Recuperar desde la base de datos local
                val entity = withContext(Dispatchers.IO) {
                    serviceDao.show(id)
                }
                onResult(entity)
            } catch (exception: Exception) {
                Log.d("error", "API call failed: ${exception.message}")
                onResult(null)
            }
        }
    }

    fun createService(service: ServiceModel, db: AppDatabase, onResult: (Boolean) -> Unit) {
        val serviceDao = db.serviceDao()
        viewModelScope.launch {
            try {
                val serviceEntity = ServiceEntity(
                    id = 99,
                    name = service.name,
                    username = service.username,
                    password = service.password,
                    description = service.description,
                    imageURL = service.imageURL
                )
                withContext(Dispatchers.IO) {
                    serviceDao.insertAll(listOf(serviceEntity)) // Guarda el servicio en la BD local
                }
                onResult(true)
            } catch (exception: Exception) {
                Log.d("error", "Insert to DB failed: ${exception.message}")
                onResult(false)
            }
        }
    }

    fun updateService(serviceId: Int, updatedService: ServiceModel, db: AppDatabase, onResult: (Boolean) -> Unit) {
        val serviceDao = db.serviceDao()
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val existingService = serviceDao.show(serviceId)
                    if (existingService != null) {
                        val updatedEntity = existingService.copy(
                            name = updatedService.name,
                            username = updatedService.username,
                            password = updatedService.password,
                            description = updatedService.description
                        )
                        serviceDao.update(updatedEntity)
                        onResult(true)
                    } else {
                        onResult(false)
                    }
                }
            } catch (exception: Exception) {
                Log.d("error", "Failed to update service: ${exception.message}")
                onResult(false)
            }
        }
    }

    fun deleteService(id:Int, onResult: (Response<ServiceModel>) -> Unit){
        try{
            viewModelScope.launch {
                val response = api.deleteService(id)
                onResult(response)
            }
        }catch(exception:Exception){
            print(exception)
        }
    }
}