package com.pager.challenge.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.pager.challenge.domain.TeamMember;
import java.util.List;

public interface TeamMemberListPresenter extends Presenter<TeamMemberListPresenter.View> {

  void onRequestTeamMembers();

  void onRequestRefreshTeamMembers();

  void onRequestTeamMemberUpdateStatus(@NonNull Context context, @NonNull TeamMember teamMember);

  interface View extends Presenter.View {
    void renderTeamMemberList(List<TeamMember> members);

    void updateTeamMemberList(List<TeamMember> members);

    void newTeamMember(TeamMember teamMember);

    void showLoading();

    void hideLoading();

    void updateStatus(String member, String status);

    void connectionFailed();

    void startingConnection();
  }
}
