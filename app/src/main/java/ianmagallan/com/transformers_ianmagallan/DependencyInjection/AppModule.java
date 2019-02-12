package ianmagallan.com.transformers_ianmagallan.DependencyInjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ianmagallan.com.transformers_ianmagallan.Presenter.ManageTransformerPresenter;
import ianmagallan.com.transformers_ianmagallan.Presenter.MainActivityPresenter;
import ianmagallan.com.transformers_ianmagallan.Services.Api;

/**
 * Class that provides the application context and presenters.
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application mApplication){
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application getApplication(){
        return this.mApplication;
    }

    @Provides
    @Singleton
    MainActivityPresenter provideMainActivityPresenter(Api mApi){
        return new MainActivityPresenter(mApi);
    }

    @Provides
    @Singleton
    ManageTransformerPresenter provideAddTransformerPresenter(Api mApi){
        return new ManageTransformerPresenter(mApi);
    }

}
