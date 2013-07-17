package com.squareup.dagger.americano.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
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
    startActivity(newHomeIntent(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
  }

  @Override public void setSession(Session session) {
    if (this.session != null) throw new IllegalStateException("Session already exists!");
    this.session = session;
    startActivity(newHomeIntent(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
  }

  public Intent newHomeIntent(int flags) {
    Intent intent = new Intent(this, TimerActivity.class);
    intent.setFlags(flags);
    return intent;
  }

  @Override public Session getSession() {
    return session;
  }

  private void resetObjectGraph(Config config) {
    Object serviceModule = Config.MOCK_BASE_URL.equals(config.getBaseUrl())
        ? new MockLoginServiceModule()
        : new RealLoginServiceModule();
    objectGraph = ObjectGraph.create(new AmericanoModule(this, config), serviceModule);
  }
}
