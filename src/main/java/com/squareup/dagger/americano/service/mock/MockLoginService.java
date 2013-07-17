package com.squareup.dagger.americano.service.mock;

import com.squareup.dagger.americano.app.Session;
import com.squareup.dagger.americano.service.LoginService;
import java.util.UUID;
import javax.inject.Inject;

public final class MockLoginService implements LoginService {
  @Inject MockLoginService() {
  }

  @Override public Session login(String emailAddress, String password) {
    try {
      Thread.sleep(500);
      return new Session(emailAddress, UUID.randomUUID().toString());
    } catch (InterruptedException e) {
      throw new AssertionError();
    }
  }
}
