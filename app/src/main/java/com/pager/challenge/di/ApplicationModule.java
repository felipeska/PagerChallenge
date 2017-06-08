package com.pager.challenge.di;

import android.app.Application;
import com.pager.challenge.data.di.ApiModule;
import com.pager.challenge.data.di.RepositoriesModule;
import com.pager.challenge.domain.executor.JobExecutor;
import com.pager.challenge.domain.executor.PostExecutionThread;
import com.pager.challenge.domain.executor.ThreadExecutor;
import com.pager.challenge.instrumentation.di.InstrumentationModule;
import com.pager.challenge.view.UIThread;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = {
    InstrumentationModule.class, RepositoriesModule.class, ApiModule.class
}) public class ApplicationModule {
  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }
}
