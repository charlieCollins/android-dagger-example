package com.squareup.dagger.americano.dev;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.squareup.dagger.americano.R;

public final class DevMenu {
  public static Menu get(Activity activity) {
    View view = activity.findViewById(R.id.debug_menu);
    final PopupMenu popupMenu;
    if (view == null) {
      ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
      view = activity.getLayoutInflater().inflate(R.layout.debug_menu, contentView, false);
      contentView.addView(view);

      popupMenu = new PopupMenu(activity, view);
      view.setTag(popupMenu);
      view.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          popupMenu.show();
        }
      });
    } else {
      popupMenu = (PopupMenu) view.getTag();
    }

    return popupMenu.getMenu();
  }
}
