package com.squareup.dagger.americano.service.mock;

import com.squareup.dagger.americano.service.LoginService;
import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class MockLoginServiceModule {
  @Provides LoginService provideLoginService(MockLoginService mockLoginService) {
    return mockLoginService;
  }
}
