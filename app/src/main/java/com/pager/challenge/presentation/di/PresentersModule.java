package com.pager.challenge.presentation.di;

import com.pager.challenge.presentation.TeamMemberListPresenter;
import com.pager.challenge.presentation.TeamMemberListPresenterImpl;
import com.pager.challenge.presentation.UpdateTeamMemberPresenter;
import com.pager.challenge.presentation.UpdateTeamMemberPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class PresentersModule {
  @Provides TeamMemberListPresenter provideActorsListPresenter(
      TeamMemberListPresenterImpl presenter) {
    return presenter;
  }

  @Provides UpdateTeamMemberPresenter provideActorsUpdatePresenter(
      UpdateTeamMemberPresenterImpl presenter) {
    return presenter;
  }
}
