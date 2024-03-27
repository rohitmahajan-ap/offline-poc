// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0
package com.amplifyframework.sample.photosharing.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.aws.GsonVariablesSerializer
import com.amplifyframework.api.graphql.GraphQLRequest
import com.amplifyframework.api.graphql.SimpleGraphQLRequest
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.category.CategoryConfiguration
import com.amplifyframework.core.category.CategoryType
import com.amplifyframework.sample.photosharing.databinding.ActivitySignInBinding
import com.amplifyframework.util.TypeMaker
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * SignInActivity is the launcher activity for customers to sign in.
 */

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAsset.setOnClickListener {
            createAssets("Test description","Gadgets","Laptop")
        }

        binding.getAsset.setOnClickListener {
            fetchAssets()
        }


    }


    private fun createAssets(description:String,category: String,name:String) {
        try {
            val mutationCreateAssets = ("mutation MyMutation(\$input: Input!) {\n" +
                    "  addAssets(input: \$input) {\n" +
                    "    category\n" +
                    "    description\n" +
                    "    name\n" +
                    "    id\n" +
                    "  }\n" +
                    "}")
            val variables = mapOf("input" to mapOf("description" to description, "category" to category,"name" to name))
            val requestGetAssets: GraphQLRequest<String> =
                SimpleGraphQLRequest(
                    mutationCreateAssets,variables,
                    TypeMaker.getParameterizedType(String::class.java),
                    GsonVariablesSerializer(),

                )
            Amplify.API.mutate(requestGetAssets, { response ->
                Log.e("AmplifyData","success ${response.data}")

            }, { error ->
                Log.e("AmplifyData","error ${error}")
            })
        }
        catch (e:Exception){


        }
    }

    private fun fetchAssets() {
       try {
           val queryGetAssets = ("query MyQuery {\n" +
                       "  getAssets {\n" +
                       "    category\n" +
                       "    description\n" +
                       "    id\n" +
                       "    name\n" +
                       "  }\n" +
                       "}")

           val requestGetAssets: GraphQLRequest<String> =
               SimpleGraphQLRequest(
                   queryGetAssets,
                   TypeMaker.getParameterizedType(String::class.java),
                   GsonVariablesSerializer()
               )

           Amplify.API.query(requestGetAssets, { response ->
               Log.e("Amplify","${response.data}")

           }, { error ->
               Log.e("Amplify","error ${error}")
           })
       }
       catch (e:Exception){
           Log.e("Amplify","error ${e}")
    }
}

}
class HeaderInterceptor(private val headers: Map<String, String>) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val requestBuilder: Request.Builder = chain.request().newBuilder()
        for ((key, value)in headers) {
            requestBuilder.addHeader(key, value)
        }
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}