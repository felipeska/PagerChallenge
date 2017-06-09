package com.pager.challenge.navigation.command;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.view.activity.TeamMemberUpdateStatusActivity;

import static com.pager.challenge.view.activity.TeamMemberUpdateStatusActivity.EXTRA_TEAM_MEMBER;

public class UpdateStatusMemberNavigationCommand extends NavigationCommand {

  private final TeamMember teamMember;

  public UpdateStatusMemberNavigationCommand(Context context, TeamMember teamMember) {
    super(context);
    this.teamMember = teamMember;
  }

  @NonNull @Override Intent onRequestIntent(Context context) {
    final Intent intent = new Intent(context, TeamMemberUpdateStatusActivity.class);
    intent.putExtra(EXTRA_TEAM_MEMBER, this.teamMember);
    return intent;
  }
}
