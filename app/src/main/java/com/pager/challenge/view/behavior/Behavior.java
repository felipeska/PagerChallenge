
package com.pager.challenge.view.behavior;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;

public abstract class Behavior {

  public void inject(@NonNull Activity activity) {
    inject(activity.getWindow().getDecorView().getRootView());
  }

  public void inject(@NonNull View rootView) {
    if (rootView != null) {
      int placeHolderViewId = onRequestPlaceHolderId();
      ViewStub placeHolder = findPlaceHolderView(rootView, placeHolderViewId);
      if (placeHolder != null) {
        attachBehaviorLayout(rootView, placeHolder);
      }
    }
  }

  @Nullable
  private ViewStub findPlaceHolderView(View rootView, int placeHolderId) {
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

  protected void onBehaviorReady(View rootView, View view) { }
}
