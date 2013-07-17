package com.squareup.dagger.americano.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.squareup.dagger.americano.login.LoginActivity;
import com.squareup.dagger.americano.service.RealLoginServiceModule;
import com.squareup.dagger.americano.service.mock.MockLoginServiceModule;
import com.squareup.dagger.americano.timers.TimerActivity;
import dagger.ObjectGraph;

public class AmericanoApp extends Application implements Navigator {
  private ObjectGraph objectGraph;
  private Session session;

  public synchronized ObjectGraph getObjectGraph() {
    if (objectGraph == null) {
      Config config = new Config.Builder()
          .baseUrl(Config.MOCK_BASE_URL)
          .build();
      resetObjectGraph(config);
    }
    return objectGraph;
  }

  public void inject(Activity activity) {
    getObjectGraph().inject(activity);
  }

  @Override public void setConfig(Config config) {
    resetObjectGraph(config);

    Intent intent = new Intent(this, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override public void setSession(Session session) {
    if (this.session != null) {
      throw new IllegalStateException("Session already exists!");
    }
    this.session = session;

    Intent intent = new Intent(this, TimerActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  private void resetObjectGraph(Config config) {
    Object serviceModule = Config.MOCK_BASE_URL.equals(config.getBaseUrl())
        ? new MockLoginServiceModule()
        : new RealLoginServiceModule();
    objectGraph = ObjectGraph.create(new AmericanoModule(this, config), serviceModule);
  }
}
