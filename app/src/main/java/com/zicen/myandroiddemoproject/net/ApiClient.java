package com.zicen.myandroiddemoproject.net;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;


/**
 * desc: Retorfit-api
 */
public class ApiClient {
    // 读取超时（毫秒）
    private static final int READ_TIME_OUT = 30000;
    // 连接超时（毫秒）
    private static final int CONNECT_TIME_OUT = 30000;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private OkHttpClient okHttpClient;
    private static SparseArray<ApiClient> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);
    private ApiService apiService;

    /*
     * 缓存设置策略
     * 1. noCache 不使用缓存，全部走网络
     * 2. noStore 不使用缓存，也不存储缓存
     * 3. onlyIfCached 只使用缓存
     * 4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合
     * 5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合
     * 6. minFresh 设置有效时间，失效则不使用 需要服务器配合
     * 7. FORCE_NETWORK 只走网络
     * 8. FORCE_CACHE 只走缓存
     */

    /**
     * 设缓存有效期为一天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";


    private ApiClient(int hostType) {

        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)//允许失败重试
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(logInterceptor)
//                .addInterceptor(new HeaderInterceptor(hostType))
//                .addInterceptor(new RetryShootInterceptor())//添加失败重试拦截器
//                .addNetworkInterceptor(requestSignInterceptor)
//                .cookieJar(MyCookieJar.newInstance())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.getHost(hostType))
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    /**
     * @param hostType host类型
     */
    public static ApiService getDefault(int hostType) {
        ApiClient retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new ApiClient(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.apiService;
    }

    /**
     * @param hostType host类型
     */
    public static ApiService getDefault(int hostType, String addHeader) {
        ApiClient retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new ApiClient(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.apiService;
    }

    /**
     * OkHttpClient
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        ApiClient retrofitManager = sRetrofitManager.get(HostType.TYPE_HOST_RELEASE);
        if (retrofitManager == null) {
            retrofitManager = new ApiClient(HostType.TYPE_HOST_RELEASE);
            sRetrofitManager.put(HostType.TYPE_HOST_RELEASE, retrofitManager);
        }
        return retrofitManager.okHttpClient;
    }


    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        if (descriptionString == null) {
            descriptionString = "";
        }
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }
}
