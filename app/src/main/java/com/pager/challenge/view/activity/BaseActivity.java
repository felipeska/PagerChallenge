package com.pager.challenge.view.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pager.challenge.R;
import com.pager.challenge.view.behavior.toolbar.ToolbarDefaultBehavior;

public abstract class BaseActivity extends AppCompatActivity {

  @BindView(R.id.rootView) View rootView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    injectViews();
    onRequestBehaviors(rootView);
    injectFragment();
  }

  private void injectViews() {
    ButterKnife.bind(this);
  }

  private void injectFragment() {
    final Fragment fragment = onRequestFragment();
    getSupportFragmentManager().beginTransaction()
        .add(R.id.content_place_holder, fragment)
        .commit();
  }

  @NonNull abstract Fragment onRequestFragment();

  protected void onRequestBehaviors(View rootView) {
    new ToolbarDefaultBehavior(this)
        .inject(rootView);
  }
}
