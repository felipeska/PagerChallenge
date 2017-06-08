package com.pager.challenge.di;

import android.app.Application;
import com.pager.challenge.PagerApplication;
import com.pager.challenge.data.di.RepositoriesProvider;
import com.pager.challenge.domain.executor.PostExecutionThread;
import com.pager.challenge.domain.executor.ThreadExecutor;
import com.pager.challenge.instrumentation.di.InstrumentationProvider;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { ApplicationModule.class }) public interface ApplicationComponent
    extends InstrumentationProvider, RepositoriesProvider {
  void inject(PagerApplication pagerApplication);

  Application getApplication();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();
}