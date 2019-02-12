package ianmagallan.com.transformers_ianmagallan;

import android.app.Application;
import android.content.Context;

import ianmagallan.com.transformers_ianmagallan.DependencyInjection.AppComponent;
import ianmagallan.com.transformers_ianmagallan.DependencyInjection.AppModule;
import ianmagallan.com.transformers_ianmagallan.DependencyInjection.DaggerAppComponent;
import ianmagallan.com.transformers_ianmagallan.DependencyInjection.NetModule;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://transformers-api.firebaseapp.com/"))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
