package com.pager.challenge.data.di;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pager.challenge.BuildConfig;
import com.pager.challenge.data.api.PagerTeamApiClient;
import com.pager.challenge.data.cache.RolesCache;
import com.pager.challenge.data.socket.SocketService;
import com.pager.challenge.data.socket.SocketServiceImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class ApiModule {

  private final RolesCache rolesCache = new RolesCache();

  @Provides @Singleton PagerTeamApiClient provideApiClient() {
    return new PagerTeamApiClient(retrofit(), rolesCache);
  }

  Retrofit retrofit() {
    return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.ENDPOINT)
        .build();
  }

  OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder().build();
  }

  @Provides @Singleton SocketService provideSocketService() {
    return new SocketServiceImpl(BuildConfig.SOCKET_ENDPOINT, okHttpClient(), new Gson(),
        rolesCache);
  }
}
