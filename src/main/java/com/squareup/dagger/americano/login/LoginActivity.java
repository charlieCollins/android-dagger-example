package com.squareup.dagger.americano.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.squareup.dagger.americano.dev.DevMenu;
import com.squareup.dagger.americano.app.AmericanoApp;
import com.squareup.dagger.americano.app.MenuMaker;
import com.squareup.dagger.americano.R;
import javax.inject.Inject;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends Activity {
  @Inject LoginController controller;
  @Inject MenuMaker menuMaker;

  private EditText emailAddress;
  private EditText password;
  private Button signIn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((AmericanoApp) getApplication()).inject(this);

    setContentView(R.layout.login);
    menuMaker.onCreateOptionsMenu(this, DevMenu.get(this));

    emailAddress = (EditText) findViewById(R.id.emailAddress);
    password = (EditText) findViewById(R.id.password);
    signIn = (Button) findViewById(R.id.signIn);
    signIn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        controller.clickedSignIn();
      }
    });
  }

  public void showErrorMessage(CharSequence message) {
    Toast.makeText(this, message, LENGTH_SHORT).show();
  }

  public String getEmailAddress() {
    return emailAddress.getText().toString();
  }

  public String getPassword() {
    return password.getText().toString();
  }

  public void setEnabled(boolean enabled) {
    emailAddress.setEnabled(enabled);
    password.setEnabled(enabled);
    signIn.setEnabled(enabled);
  }

  @Override protected void onResume() {
    super.onResume();
    controller.onResume(this);
  }

  @Override protected void onPause() {
    controller.onPause(this);
    super.onPause();
  }
}
