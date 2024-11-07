package com.example.vendorapp.common.base

import android.content.Context
import androidx.annotation.Keep
import com.example.vendorapp.common.data.remote.api.TokenRefreshApi
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Keep
class RetrofitInstances @Inject constructor() {
    @Keep
    companion object {
        val BASE_URL = "http://eindi.thecodelab.me/api/v3/"
    }
    @Keep
    fun <Api> buildApi(
        api: Class<Api>,
        context: Context,
        token: String? = null,
    ): Api {

        val authenticator = TokenAuthenticator(context, buildTokenApi(context, token))

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(authenticator, token, context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    }

    @Keep
    private fun buildTokenApi(context: Context, token: String?): TokenRefreshApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(tc = token, context = context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TokenRefreshApi::class.java)
    }


    @Keep
    private fun getRetrofitClient(
        authenticator: Authenticator? = null,
        tc: String?,
        context: Context
    ): OkHttpClient {
        var token = tc
        /*  val userPreferences = UserPreferences(context)
          //Log.e("token new", tc.toString())

          // Read the CA bundle from the raw resource
          val caInput: InputStream = context.resources.openRawResource(R.raw.star_xpayback_com)

          // Load the CA certificates from the bundle
          val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
          val caCerts: MutableList<X509Certificate> = mutableListOf()
          while (caInput.available() > 0) {
              val cert: X509Certificate = cf.generateCertificate(caInput) as X509Certificate
              caCerts.add(cert)
          }

          // Build the certificate pinner
          val certificatePinnerBuilder = CertificatePinner.Builder()
          for (cert in caCerts) {
              val pin: String = CertificatePinner.pin(cert)
              certificatePinnerBuilder.add("api.xpayback.com", pin)
          }

          runBlocking {
              if (tc == null) {
                  if(BuildConfig.BUILD_TYPE=="staging"||BuildConfig.BUILD_TYPE=="dev"||BuildConfig.BUILD_TYPE=="uat"){
                      Log.e("token new", "new " + userPreferences.accessToken.first().toString())
                  }
                  token = userPreferences.accessToken.first()
              }
          }*/

        return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            // .certificatePinner(certificatePinnerBuilder.build())
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                chain.proceed(request.newBuilder().also {
                    it.addHeader("Accept", "application/json")
                    //   it.addHeader("Authorization", "Bearer $token")
                }.build())
            }.also { client ->
                authenticator?.let {
                    client.authenticator(it)
                }

                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
                /* if (BuildConfig.BUILD_TYPE=="staging"||BuildConfig.BUILD_TYPE=="dev"||BuildConfig.BUILD_TYPE=="uat"||BuildConfig.BUILD_TYPE=="production") {
                     val logging = HttpLoggingInterceptor()
                     logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                     client.addInterceptor(logging)
                 }*/
            }.build()

        // }
        //  return client
    }

}