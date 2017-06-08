package com.pager.challenge.presentation;

import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.domain.interactor.DefaultObserver;
import com.pager.challenge.domain.interactor.GetTeamMembersUseCase;
import java.util.List;
import javax.inject.Inject;

public class TeamMemberListPresenterImpl extends BasePresenter<TeamMemberListPresenter.View>
    implements TeamMemberListPresenter {

  private boolean loadingInProgress = false;
  private final GetTeamMembersUseCase useCase;

  @Inject public TeamMemberListPresenterImpl(GetTeamMembersUseCase useCase) {
    this.useCase = useCase;
  }

  @Override protected void onPresenterDestroy() {
    super.onPresenterDestroy();
    this.useCase.dispose();
  }

  @Override public void onRequestTeamMembers() {
    getView().showLoading();
    loadingInProgress = true;
    useCase.execute(new TeamMemberListObserver(false), null);
  }

  @Override public void onRequestRefreshTeamMembers() {
    if (!loadingInProgress) {
      useCase.execute(new TeamMemberListObserver(true), null);
    }
  }

  private final class TeamMemberListObserver extends DefaultObserver<List<TeamMember>> {

    private final boolean isUpdate;

    private TeamMemberListObserver(boolean isUpdate) {
      this.isUpdate = isUpdate;
    }

    @Override public void onError(Throwable e) {
      getView().hideLoading();
    }

    @Override public void onNext(List<TeamMember> teamMembers) {
      if (isUpdate) {
        getView().updateTeamMemberList(teamMembers);
      } else {
        loadingInProgress = false;
        getView().hideLoading();
        getView().renderTeamMemberList(teamMembers);
      }
    }
  }
}
