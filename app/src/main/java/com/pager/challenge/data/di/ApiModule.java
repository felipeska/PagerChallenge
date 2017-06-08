package com.pager.challenge.data.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pager.challenge.BuildConfig;
import com.pager.challenge.data.api.PagerTeamApiClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class ApiModule {

  @Provides @Singleton PagerTeamApiClient provideApiClient() {
    return new PagerTeamApiClient(retrofit());
  }

  Retrofit retrofit() {
    return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.ENDPOINT)
        .build();
  }
}
