package com.squareup.dagger.americano.app;

import android.content.Intent;

public interface Navigator {
  void setConfig(Config config);
  void setSession(Session session);
  Session getSession();
  Intent newHomeIntent(int flags);
}
