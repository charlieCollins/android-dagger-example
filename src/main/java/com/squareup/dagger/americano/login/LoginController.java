package com.squareup.dagger.americano.login;

import android.os.Handler;
import com.squareup.dagger.americano.app.Navigator;
import com.squareup.dagger.americano.app.Session;
import com.squareup.dagger.americano.service.LoginService;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoginController {
  @Inject ExecutorService executor;
  @Inject Handler mainThread;
  @Inject LoginService loginService;
  @Inject Navigator navigator;

  private LoginActivity activity;

  public void onResume(LoginActivity activity) {
    this.activity = activity;
  }

  public void onPause(LoginActivity activity) {
    this.activity = null;
  }

  public void clickedSignIn() {
    if (activity == null) return;

    activity.setEnabled(false);
    final String emailAddress = activity.getEmailAddress();
    final String password = activity.getPassword();

    executor.execute(new Runnable() {
      @Override public void run() {
        final Session session = loginService.login(emailAddress, password);
        mainThread.post(new Runnable() {
          @Override public void run() {
            if (activity == null) return;
            activity.setEnabled(true);
            navigator.setSession(session);
          }
        });
      }
    });
  }
}
