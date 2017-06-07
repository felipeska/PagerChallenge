package com.pager.challenge.presentation.di;

import com.pager.challenge.presentation.TeamMemberListPresenter;
import com.pager.challenge.presentation.TeamMemberListPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class PresentersModule {
  @Provides TeamMemberListPresenter provideActorsListPresenter(
      TeamMemberListPresenterImpl presenter) {
    return presenter;
  }
}
