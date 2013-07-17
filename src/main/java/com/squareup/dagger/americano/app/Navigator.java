package com.squareup.dagger.americano.app;

import com.squareup.dagger.americano.app.Config;
import com.squareup.dagger.americano.app.Session;

public interface Navigator {
  void setConfig(Config config);
  void setSession(Session session);
}
