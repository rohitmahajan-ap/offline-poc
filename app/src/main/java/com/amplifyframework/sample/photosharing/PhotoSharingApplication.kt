// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0
package com.amplifyframework.sample.photosharing

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotoSharingApplication : Application() {
    private val TAG = "AmplifyQuickstart"

    override fun onCreate() {
        super.onCreate()
//        configureHeaders()
        initAmplify()
    }
    fun configureHeaders(){
        com.amplifyframework.core.Amplify.API.addPlugin(AWSApiPlugin.builder().configureClient("my-api")
        { okHttpBuilder ->
            okHttpBuilder.addInterceptor {
                it.proceed(it.request()
                    .newBuilder()
                    .addHeader("x-api-key", "da2-jgllc3ezsvecppgqtkqemirpdi")
                    .build())
            }
        }.build())
    }
    private fun initAmplify() {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin(AWSApiPlugin())
//            configureHeaders()

            Amplify.configure(applicationContext)
            Log.e(TAG, "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e(TAG, "Could not initialize Amplify", error)
        }
    }
}