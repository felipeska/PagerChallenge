package com.pager.challenge.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.pager.challenge.domain.TeamMember;

public interface Navigator {
  void navigateUpdateTeamMemberStatus(@NonNull Context context, @NonNull TeamMember teamMember);
}
