package com.squareup.dagger.americano.app;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import com.squareup.dagger.americano.dev.EditConfigActivity;
import javax.inject.Inject;

public class MenuMaker {
  @Inject MenuMaker() {
  }

  public void onCreateOptionsMenu(final Activity activity, Menu menu) {
    if (!(activity instanceof EditConfigActivity)) {
      menu.add("Developer Config")
          .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override public boolean onMenuItemClick(MenuItem menuItem) {
              activity.startActivity(new Intent(activity, EditConfigActivity.class));
              return true;
            }
          });
    }
  }
}
