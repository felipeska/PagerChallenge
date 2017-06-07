package com.pager.challenge.presentation;

import javax.inject.Inject;

public class TeamMemberListPresenterImpl extends BasePresenter<TeamMemberListPresenter.View>
    implements TeamMemberListPresenter {

  private boolean loadingInProgress = false;

  @Inject public TeamMemberListPresenterImpl() {
  }

  @Override public void onRequestTeamMembers() {
    getView().showLoading();
    loadingInProgress = true;
  }

  @Override public void onRequestRefreshTeamMembers() {

  }
}
