package com.pager.challenge.domain.di;

import com.pager.challenge.domain.interactor.BaseUseCase;
import com.pager.challenge.domain.interactor.GetTeamMembersUseCase;
import dagger.Module;
import dagger.Provides;

@Module public class UseCasesModule {

  @Provides BaseUseCase provideGetTeamMembersUseCase(GetTeamMembersUseCase useCase) {
    return useCase;
  }
}
