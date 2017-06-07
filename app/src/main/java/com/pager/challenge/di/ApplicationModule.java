package com.pager.challenge.di;

import android.app.Application;
import com.pager.challenge.instrumentation.di.InstrumentationModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = {
    InstrumentationModule.class
}) public class ApplicationModule {
  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }
}
