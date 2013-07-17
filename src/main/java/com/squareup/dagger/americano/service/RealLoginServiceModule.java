package com.squareup.dagger.americano.service;

import com.squareup.dagger.americano.app.Config;
import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class RealLoginServiceModule {
  @Provides LoginService provideLoginService(Config config) {
    throw new UnsupportedOperationException(
        "TODO: create a service that connects to " + config.getBaseUrl());
  }
}
