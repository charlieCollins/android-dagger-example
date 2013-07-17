package com.squareup.dagger.americano.dev;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.squareup.dagger.americano.app.Navigator;
import com.squareup.dagger.americano.R;
import com.squareup.dagger.americano.app.AmericanoApp;
import com.squareup.dagger.americano.app.Config;
import java.net.InetSocketAddress;
import javax.inject.Inject;

public class EditConfigActivity extends Activity {
  @Inject Config config;
  @Inject Navigator app;

  private EditText baseUrl;
  private EditText httpProxy;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((AmericanoApp) getApplication()).inject(this);

    setContentView(R.layout.config);

    baseUrl = (EditText) findViewById(R.id.baseUrl);
    httpProxy = (EditText) findViewById(R.id.httpProxy);

    baseUrl.setText(config.getBaseUrl());
    if (config.getHttpProxy() != null) {
      InetSocketAddress address = (InetSocketAddress) config.getHttpProxy().address();
      httpProxy.setText(address.getHostName() + ":" + address.getPort());
    }

    TextView baseUrlMock = (TextView) findViewById(R.id.baseUrlMock);
    TextView baseUrlStaging = (TextView) findViewById(R.id.baseUrlStaging);
    TextView baseUrlProduction = (TextView) findViewById(R.id.baseUrlProduction);
    TextView baseUrlEmulator = (TextView) findViewById(R.id.baseUrlEmulator);
    setTextOnTouch(baseUrlMock, baseUrl, Config.MOCK_BASE_URL);
    setTextOnTouch(baseUrlStaging, baseUrl, Config.STAGING_BASE_URL);
    setTextOnTouch(baseUrlProduction, baseUrl, Config.PRODUCTION_BASE_URL);
    setTextOnTouch(baseUrlEmulator, baseUrl, Config.EMULATOR_BASE_URL);
  }

  private void setTextOnTouch(TextView touchable, final EditText target, final String text) {
    touchable.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.setText(text);
      }
    });
  }

  @Override public void onBackPressed() {
    super.onBackPressed();

    Config config = new Config.Builder()
        .httpProxy(httpProxy.getText().toString())
        .baseUrl(baseUrl.getText().toString())
        .build();

    app.setConfig(config);
  }
}
