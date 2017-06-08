package com.pager.challenge.data.di;

import com.pager.challenge.domain.repository.TeamRepository;

public interface RepositoriesProvider {
  TeamRepository getActorsRepository();
}
