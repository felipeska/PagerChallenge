package com.pager.challenge.view.di;

import com.pager.challenge.di.ApplicationComponent;
import com.pager.challenge.di.scope.PerView;
import com.pager.challenge.view.fragment.TeamMemberListFragment;
import com.pager.challenge.view.fragment.TeamMemberUpdateStatusFragment;
import dagger.Component;

@PerView @Component(dependencies = ApplicationComponent.class, modules = { ViewModule.class })
public interface ViewComponent {
  void inject(TeamMemberListFragment view);

  void inject(TeamMemberUpdateStatusFragment view);
}
