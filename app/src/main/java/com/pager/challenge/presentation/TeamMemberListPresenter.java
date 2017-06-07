package com.pager.challenge.presentation;

import com.pager.challenge.domain.TeamMember;
import java.util.List;

public interface TeamMemberListPresenter extends Presenter<TeamMemberListPresenter.View> {

  void onRequestTeamMembers();

  void onRequestRefreshTeamMembers();

  interface View extends Presenter.View {
    void renderTeamMemberList(List<TeamMember> members);

    void updateTeamMemberList(List<TeamMember> members);

    void showLoading();

    void hideLoading();
  }
}
