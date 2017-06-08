package com.pager.challenge.data.di;

import com.pager.challenge.data.team.repository.TeamRepositoryImpl;
import com.pager.challenge.domain.repository.TeamRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class RepositoriesModule {
  @Provides @Singleton TeamRepository provideActorsRepository(TeamRepositoryImpl repository) {
    return repository;
  }
}