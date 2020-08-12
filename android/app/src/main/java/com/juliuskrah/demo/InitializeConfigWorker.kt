package com.juliuskrah.demo

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import java.io.InputStream
import java.lang.Exception

class InitializeConfigWorker(
    applicationContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(applicationContext, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            fetchFile().use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val applicationProperties = Gson().fromJson<ApplicationProperties>(
                        jsonReader,
                        ApplicationProperties::class.java
                    )
                    Log.d(TAG, "Properties: $applicationProperties")
                    Singleton.properties = applicationProperties
                    Result.success()
                }
            }
        }catch (exception: Exception) {
            Log.e(TAG, "Error initializing configuration", exception)
            Result.failure()
        }
    }

    private fun fetchFile(): InputStream {
        return if (BuildConfig.DEBUG) {
            applicationContext.assets.open("application-debug.json")
        } else {
            applicationContext.assets.open("application.json")
        }
    }

    companion object {
        private val TAG = InitializeConfigWorker::class.simpleName
    }
}