package com.squareup.dagger.americano.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.squareup.dagger.americano.dev.EditConfigActivity;
import com.squareup.dagger.americano.app.Navigator;
import com.squareup.dagger.americano.login.LoginActivity;
import com.squareup.dagger.americano.timers.TimerActivity;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
    injects = {
        EditConfigActivity.class,
        LoginActivity.class,
        TimerActivity.class
    },
    complete = false
)
public class AmericanoModule {
  private final AmericanoApp americanoApp;
  private final Config config;

  public AmericanoModule(AmericanoApp americanoApp, Config config) {
    this.americanoApp = americanoApp;
    this.config = config;
  }

  @Provides @Singleton ExecutorService provideExecutorService() {
    return Executors.newCachedThreadPool();
  }

  @Provides @Named("Application") Context provideContext() {
    return americanoApp;
  }

  @Provides Navigator provideNavigator() {
    return americanoApp;
  }

  @Provides @Singleton Config provideConfig() {
    return config;
  }

  @Provides @Singleton Handler provideHandler() {
    return new Handler(Looper.getMainLooper());
  }
}
