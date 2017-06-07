package com.pager.challenge.di;

import android.app.Application;
import com.pager.challenge.PagerApplication;
import com.pager.challenge.instrumentation.di.InstrumentationProvider;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent extends InstrumentationProvider {
  void inject(PagerApplication pagerApplication);
  Application getApplication();
}