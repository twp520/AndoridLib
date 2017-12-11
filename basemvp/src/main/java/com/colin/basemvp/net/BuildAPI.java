package com.colin.basemvp.net;


import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tianweiping on 2017/12/11.
 */
public class BuildAPI {


    private static Retrofit headerRetrofit;

    /**
     * 构造有自定义header字段token的Retrofi
     *
     * @param
     * @return
     */
    public static APIServers buildHeaderAPISevers() {
        if (headerRetrofit == null) {
            headerRetrofit = new Retrofit.Builder().baseUrl(NetConstant.BASEURL)
                    .addConverterFactory(new DesConverterFactor())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getTokenClient())
                    .build();
        }
        return headerRetrofit.create(APIServers.class);
    }

    /**
     * 添加自定义header
     *
     * @param
     * @return
     */
    public static OkHttpClient getTokenClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new HttpLogInterceptor("httpLog", true))
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        return client;
    }

    //构建请求体
    public static RequestBody buildBody(String object) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object);
    }

    @NonNull
    public static String buildRequestMessage(Object object) {
        String req = new Gson().toJson(object);
//        return Des3.encode(req);
        return "";
    }
}
