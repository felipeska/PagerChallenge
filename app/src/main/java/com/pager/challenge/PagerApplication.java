package com.pager.challenge;

import android.app.Application;
import com.pager.challenge.di.ApplicationComponent;
import com.pager.challenge.di.ApplicationModule;
import com.pager.challenge.di.DaggerApplicationComponent;

public class PagerApplication extends Application {
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjection();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  private void initializeInjection() {
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);
  }
}
