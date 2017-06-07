package com.pager.challenge.view.behavior.toolbar;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pager.challenge.R;
import com.pager.challenge.view.behavior.Behavior;

abstract class ToolbarBehavior extends Behavior {
  private final AppCompatActivity activity;
  @BindView(R.id.toolbar) Toolbar toolbar;

  ToolbarBehavior(@NonNull Activity activity) {
    if (!(activity instanceof AppCompatActivity)) {
      throw new IllegalArgumentException();
    }
    this.activity = (AppCompatActivity) activity;
  }

  @Override protected int onRequestPlaceHolderId() {
    return R.id.toolbar_place_holder;
  }

  @Override protected void onBehaviorReady(View rootView, View view) {
    ButterKnife.bind(this, rootView);
    activity.setSupportActionBar(toolbar);
  }

  public abstract static class Behavior {
    public void inject(@NonNull Activity activity) {
      inject(activity.getWindow().getDecorView().getRootView());
    }

    public void inject(@NonNull View rootView) {
      int placeHolderViewId = onRequestPlaceHolderId();
      ViewStub placeHolder = findPlaceHolderView(rootView, placeHolderViewId);
      if (placeHolder != null) {
        attachBehaviorLayout(rootView, placeHolder);
      }
    }

    @Nullable private ViewStub findPlaceHolderView(View rootView, int placeHolderId) {
      ViewStub result = null;
      View view = rootView.findViewById(placeHolderId);
      if (view instanceof ViewStub) {
        result = (ViewStub) view;
      }
      return result;
    }

    private void attachBehaviorLayout(final View rootView, ViewStub placeHolderView) {
      int behaviorLayoutResourceId = onRequestLayoutResourceId();
      placeHolderView.setLayoutResource(behaviorLayoutResourceId);
      placeHolderView.setOnInflateListener(new ViewStub.OnInflateListener() {
        @Override public void onInflate(ViewStub stub, View view) {
          stub.setVisibility(View.VISIBLE);
          onBehaviorReady(rootView, view);
        }
      });
      placeHolderView.inflate();
    }

    @IdRes protected abstract int onRequestPlaceHolderId();

    @LayoutRes protected abstract int onRequestLayoutResourceId();

    protected void onBehaviorReady(View rootView, View view) {
    }
  }
}
