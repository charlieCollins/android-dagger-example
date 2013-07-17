package com.squareup.dagger.americano.timers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.squareup.dagger.americano.R;
import com.squareup.dagger.americano.app.AmericanoApp;
import javax.inject.Inject;

public final class TimerActivity extends Activity {
  @Inject TimerController timerController;

  private TextView timer;
  private Button start;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((AmericanoApp) getApplication()).inject(this);

    setContentView(R.layout.timer);
    timer = (TextView) findViewById(R.id.timer);
    start = (Button) findViewById(R.id.start);

    start.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        timerController.restartTimer();
      }
    });
  }

  @Override protected void onResume() {
    super.onResume();
    timerController.onResume(this);
  }

  @Override protected void onPause() {
    timerController.onPause(this);
    super.onPause();
  }

  public void setElapsedNanos(long elapsedNanos) {
    timer.setText(String.format("%.2f", elapsedNanos / 1000000000d));
  }
}
