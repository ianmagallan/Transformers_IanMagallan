package ianmagallan.com.transformers_ianmagallan.DependencyInjection;

import javax.inject.Singleton;

import dagger.Component;
import ianmagallan.com.transformers_ianmagallan.View.ManageTransformerActivity;
import ianmagallan.com.transformers_ianmagallan.View.MainActivity;

/**
 * Class that defines what Activities can use the dependency injection.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(ManageTransformerActivity manageTransformerActivity);
}