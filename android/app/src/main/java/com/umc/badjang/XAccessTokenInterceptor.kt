package com.umc.badjang

import com.umc.badjang.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.umc.badjang.ApplicationClass.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences.getString(X_ACCESS_TOKEN,null)
        println("jwtToken interceptor : $jwtToken")
        if(jwtToken !=null){
            builder.addHeader("X-ACCESS-TOKEN",jwtToken)
        }
        return chain.proceed(builder.build())
    }
}