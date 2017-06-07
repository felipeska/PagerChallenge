package com.pager.challenge.instrumentation.di;

import com.pager.challenge.instrumentation.ImageDownloader;
import com.pager.challenge.instrumentation.PicassoImageDownloader;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class InstrumentationModule {

  @Provides @Singleton ImageDownloader provideImageDownloader(
      PicassoImageDownloader imageDownloader) {
    return imageDownloader;
  }
}
