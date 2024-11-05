package com.ita.myapp.classes.ui.screens

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data

class BackgroundTaskWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        Log.d("BackgroundTaskWorker", "Tarea en segundo plano iniciada")

        // Simulación de trabajo en segundo plano
        for (i in 1..5) {
            try {
                Thread.sleep(1000) // Simula trabajo de 1 segundo
                Log.d("BackgroundTaskWorker", "Trabajo $i en progreso")

                // Actualiza el progreso con cada paso
                setProgressAsync(Data.Builder().putInt("progress", i).build())
            } catch (e: InterruptedException) {
                Log.e("BackgroundTaskWorker", "Trabajo interrumpido: ${e.message}")
                return Result.failure() // Retorna fallo si se interrumpe
            }
        }

        return Result.success() // Indica que la tarea se completó con éxito
    }
}
