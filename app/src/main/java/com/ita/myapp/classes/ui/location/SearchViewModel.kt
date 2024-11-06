package com.ita.myapp.classes.ui.location

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ita.myapp.classes.ui.location.GoogleGeoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class SearchViewModel: ViewModel() {

    var lat by mutableDoubleStateOf(0.0)
        private set // significa que solo puede ser modificada dentro de esta clase
    var long by mutableDoubleStateOf(0.0)
        private set
    var address by mutableStateOf("")
        private set
    var show by mutableStateOf(false)
        private set

    fun getLocation(search: String) {
        viewModelScope.launch {
            try {
                val apikey = "AIzaSyA4uRUwSHcbfQRuEn2By0U0-oFPy73aoi4"
                val url = "https://maps.googleapis.com/maps/api/geocode/json?address=$search&key=$apikey"
                val response = withContext(Dispatchers.IO) { URL(url).readText() }

                Log.d("SearchViewModel", "Respuesta de la API: $response")

                val results = Gson().fromJson(response, GoogleGeoResult::class.java)

                if (results.results.isNotEmpty()) {
                    show = true
                    lat = results.results[0].geometry.location.lat
                    long = results.results[0].geometry.location.lng
                    address = results.results[0].formatted_address
                    Log.d("SearchViewModel", "Coordenadas obtenidas: Lat: $lat, Long: $long, Dirección: $address")
                } else {
                    Log.d("Fail", "No se encontró una dirección válida.")
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error al obtener la ubicación: ${e.message}")
            }
        }
    }

}