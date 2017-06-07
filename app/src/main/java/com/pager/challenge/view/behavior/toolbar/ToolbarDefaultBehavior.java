package com.pager.challenge.view.behavior.toolbar;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.pager.challenge.R;

public class ToolbarDefaultBehavior extends ToolbarBehavior {
  public ToolbarDefaultBehavior(@NonNull Activity activity) {
    super(activity);
  }

  @Override protected int onRequestLayoutResourceId() {
    return R.layout.toolbar_default;
  }
}
