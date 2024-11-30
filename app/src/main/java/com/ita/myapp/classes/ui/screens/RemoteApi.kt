package com.ita.myapp.classes.ui.screens



interface RemoteApi {
    suspend fun createService(service: Service)
    suspend fun updateService(service: Service)
    suspend fun deleteService(id: Int)
}