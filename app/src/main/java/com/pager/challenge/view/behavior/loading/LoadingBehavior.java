package com.pager.challenge.view.behavior.loading;

import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pager.challenge.R;
import com.pager.challenge.view.behavior.Behavior;

public class LoadingBehavior extends Behavior {
  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.content_place_holder) View contentView;

  @Override
  protected int onRequestPlaceHolderId() {
    return R.id.loading_place_holder;
  }

  @Override
  protected int onRequestLayoutResourceId() {
    return R.layout.behavior_loading;
  }

  @Override
  protected void onBehaviorReady(View rootView, View view) {
    ButterKnife.bind(this, rootView);
  }

  public void showLoading() {
    contentView.setVisibility(View.GONE);
    loadingView.setVisibility(View.VISIBLE);
  }

  public void hideLoading() {
    contentView.setVisibility(View.VISIBLE);
    loadingView.setVisibility(View.GONE);
  }
}
