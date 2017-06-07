package com.pager.challenge.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.pager.challenge.R;
import com.pager.challenge.view.fragment.TeamMemberListFragment;

public class TeamMemberListActivity extends BaseActivity {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(R.string.main_activity_title);
  }

  @NonNull @Override Fragment onRequestFragment() {
    return TeamMemberListFragment.newInstance();
  }
}
