package com.pager.challenge.navigation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.navigation.command.NavigationCommand;
import com.pager.challenge.navigation.command.UpdateStatusMemberNavigationCommand;
import javax.inject.Inject;

public class ApplicationNavigator implements Navigator {

  @Inject public ApplicationNavigator() {
  }

  private void navigate(NavigationCommand navigationCommand) {
    navigationCommand.execute();
  }

  @Override public void navigateUpdateTeamMemberStatus(@NonNull Context context,
      @NonNull TeamMember teamMember) {
    final UpdateStatusMemberNavigationCommand navigationCommand =
        new UpdateStatusMemberNavigationCommand(context, teamMember);
    navigate(navigationCommand);
  }
}
