package com.bkdev.translation.networks.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.bkdev.translation.networks.Api;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String HEADER_UA = "User-Agent";
    private static final String TAG = ApiClient.class.getSimpleName();

    private static final int TIMEOUT_CONNECTION = 10000;

    private static ApiClient sApiClient;
    /**
     * Api service
     */
    public Api mApi;
    /**
     * android application context
     */
    private static Context sContext;


    public void init(final ApiConfig apiConfig) {
        sContext = apiConfig.context;
        // initialize OkHttpClient
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                Map<String, String> headers = SessionStore.getInstance().getHeader(apiConfig.context);
                if (headers != null && headers.size() > 0) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        builder.addHeader(entry.getKey(), entry.getValue());
                    }
                }
                builder.addHeader(HEADER_UA, createUserAgent());
                return chain.proceed(builder.build());
            }
        });
        b.readTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);
        b.writeTimeout(TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);

        // Gson rules
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(apiConfig.baseUrl)
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        
        mApi = retrofit.create(Api.class);
    }

    private String createUserAgent() {
        PackageManager pm = sContext.getPackageManager();
        String versionName = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(sContext.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return System.getProperty("http.agent") + " " + sContext.getPackageName() + "/" + versionName;
    }

    /**
     * method this is request api
     *
     * @return Api class
     */
    public static Api call() {
        return getInstance().mApi;
    }

    /**
     * method this is manger header
     *
     * @return Session
     */
    public static SessionStore header() {
        return SessionStore.getInstance(sContext);
    }

    /**
     * get singleton instance
     *
     * @return current apiclient
     */
    public static synchronized ApiClient getInstance() {
        if (sApiClient == null) {
            sApiClient = new ApiClient();
        }
        return sApiClient;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .addInterceptor(interceptor)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
