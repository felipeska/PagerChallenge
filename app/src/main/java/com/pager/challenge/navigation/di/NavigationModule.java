package com.pager.challenge.navigation.di;

import com.pager.challenge.navigation.ApplicationNavigator;
import com.pager.challenge.navigation.Navigator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class NavigationModule {
  @Singleton @Provides Navigator provideNavigator(ApplicationNavigator navigator) {
    return navigator;
  }
}
