package com.example.streethawkerssurveyapp.services_pack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {


  public static Retrofit retrofit = null;
  public static Retrofit retrofit2 = null;


  public static OkHttpClient client = getClient();
  public static final String BASE_URL = "https://dashboard.delhistreethawker.com/api/";
//  public static final String BASE_URL = "https://staging.delhistreethawker.com/api/";
  public static final String AADHAR_URL = "https://kyc-api.aadhaarkyc.io/api/v1/aadhaar-v2/";

  public static OkHttpClient getClient() {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .build();

    return client;
  }

  public static Gson gson = new GsonBuilder()
    .setLenient()
    .create();

  public static Retrofit getApiClient() {

    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .client(client)
//      .client(getUnsafeOkHttpClient())
              .addConverterFactory(GsonConverterFactory.create(gson))
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

              .build();
    }
      return  retrofit;
  }

  public static Retrofit getApiClient2(){

    if (retrofit2 == null){
      retrofit2 = new Retrofit.Builder()
              .baseUrl(AADHAR_URL)
              .client(client)
//      .client(getUnsafeOkHttpClient())
              .addConverterFactory(GsonConverterFactory.create(gson))
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

              .build();
    }

    return retrofit2;
  }
}
