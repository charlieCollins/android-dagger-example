package com.squareup.dagger.americano.timers;

import android.os.Handler;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class TimerController {
  @Inject Handler handler;

  private Runnable runnable = new Runnable() {
    @Override public void run() {
      if (handler == null || !timerRunning) return;
      activity.setElapsedNanos(System.nanoTime() - timerStartNanos);
      handler.postDelayed(this, 16);
    }
  };

  private TimerActivity activity;
  private long timerStartNanos;
  private boolean timerRunning;

  public void onResume(TimerActivity activity) {
    this.activity = activity;
    handler.post(runnable);
  }

  public void onPause(TimerActivity activity) {
    this.activity = null;
    handler.removeCallbacks(runnable);
  }

  public void restartTimer() {
    if (!timerRunning) {
      timerRunning = true;
      handler.post(runnable);
    }
    timerStartNanos = System.nanoTime();
  }
}
