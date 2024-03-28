// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0
package com.amplifyframework.sample.photosharing

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.aws.ApiAuthProviders
import com.amplifyframework.api.aws.sigv4.ApiKeyAuthProvider
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.kotlin.core.Amplify
import java.util.concurrent.CompletableFuture


class PhotoSharingApplication : Application() {
    private val TAG = "AmplifyQuickstart"

    override fun onCreate() {
        super.onCreate()
//        configureHeaders()
        initAmplify()
    }

    private fun initAmplify() {
        try {
//            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin(AWSApiPlugin())
//            configureHeaders()
            Amplify.configure(applicationContext)
            Log.e("AmplifyData", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("AmplifyData", "Could not initialize Amplify", error)
        }
    }
    fun configureHeaders(){
        val apiKeyAuthProvider=ApiKeyAuthProvider(){
           "da2-jgllc3ezsvecppgqtkqemirpdi"
        }
        val authProviders = ApiAuthProviders.builder().apiKeyAuthProvider(apiKeyAuthProvider).build()
        val plugin = AWSApiPlugin.builder()
            .apiAuthProviders(authProviders)
            .build()
        Amplify.addPlugin(plugin)
    }
}