package com.pager.challenge.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.pager.challenge.PagerApplication;
import com.pager.challenge.R;
import com.pager.challenge.di.ApplicationComponent;

public abstract class BaseFragment extends Fragment {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onRequestInjection(getApplicationComponent());
    setHasOptionsMenu(true);
  }

  private ApplicationComponent getApplicationComponent() {
    return ((PagerApplication) getActivity().getApplication()).getApplicationComponent();
  }

  void onRequestInjection(ApplicationComponent applicationComponent) {
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    int layoutResourceId = onRequestLayoutResourceId();
    return inflater.inflate(layoutResourceId, container, false);
  }

  @LayoutRes protected abstract int onRequestLayoutResourceId();

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    doViewInjection(view);
    onPresenterShouldBeAttached();
    onViewReady();
  }

  private void doViewInjection(View view) {
    View bindingView = view;
    if (isAdded()) {
      bindingView = getActivity().findViewById(R.id.rootView);
    }
    ButterKnife.bind(this, bindingView);
  }

  protected void onPresenterShouldBeAttached() {
  }

  protected void onViewReady() {
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    onPresenterShouldBeDetached();
  }

  protected void onPresenterShouldBeDetached() {
  }
}
