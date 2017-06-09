package com.pager.challenge.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.pager.challenge.R;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.view.fragment.TeamMemberUpdateStatusFragment;

public class TeamMemberUpdateStatusActivity extends BaseActivity {

  public static final String EXTRA_TEAM_MEMBER = "team:member";

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(R.string.update_status_activity_title);
  }

  @NonNull @Override Fragment onRequestFragment() {
    TeamMember teamMember = getTeamMember();
    return TeamMemberUpdateStatusFragment.newInstance(teamMember);
  }

  private TeamMember getTeamMember() {
    return getIntent().getExtras().getParcelable(EXTRA_TEAM_MEMBER);
  }
}
