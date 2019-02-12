package ianmagallan.com.transformers_ianmagallan.DependencyInjection;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ianmagallan.com.transformers_ianmagallan.Helpers.TLSSocketFactory;
import ianmagallan.com.transformers_ianmagallan.Services.Api;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that is in charge of initialize all related with network calls.
 */
@Module
public class NetModule {
    private final String mBaseUrl;
    private final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0cmFuc2Zvcm1lcnNJZCI6Ii1MWUpnWEwtMGE3c0VQcV9KVVZYIiwiaWF0IjoxNTQ5NzU2NjczfQ.akD_MnMJQNJzybrZ7JNvFcMtn4TiiGFBcdiul4-YV74";

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        clientBuilder.addInterceptor(interceptor);

        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + AUTH_TOKEN).build();
                return chain.proceed(request);
            }
        });
        try {
            clientBuilder.sslSocketFactory(new TLSSocketFactory());
        }catch(Exception ex){

        }

        return clientBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(this.mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Api provideApi(Retrofit mRetrofit){
        return mRetrofit.create(Api.class);
    }

}