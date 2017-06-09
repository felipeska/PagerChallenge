package com.pager.challenge.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.pager.challenge.domain.event.model.Event;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.domain.event.EventListener;
import com.pager.challenge.domain.event.model.NewUserEvent;
import com.pager.challenge.domain.event.model.UpdateStatusEvent;
import com.pager.challenge.domain.event.model.UserEvent;
import com.pager.challenge.domain.interactor.DefaultObserver;
import com.pager.challenge.domain.interactor.GetTeamMembersUseCase;
import com.pager.challenge.navigation.Navigator;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class TeamMemberListPresenterImpl extends BasePresenter<TeamMemberListPresenter.View>
    implements TeamMemberListPresenter {

  private boolean loadingInProgress = false;
  private final GetTeamMembersUseCase useCase;
  private final Navigator navigator;

  @Inject public TeamMemberListPresenterImpl(GetTeamMembersUseCase useCase, Navigator navigator) {
    this.useCase = useCase;
    this.navigator = navigator;
  }

  @Override protected void onPresenterReady() {
    super.onPresenterReady();
    this.useCase.startConnection(new EventListener() {

      @Override public void onEventStart() {
        getView().startingConnection();
      }

      @Override public void onEventFailure(Throwable t) {
        getView().connectionFailed();
      }

      @Override public void onNewEvent(Event event) {
        System.out.print(event.event);
        if (event instanceof UpdateStatusEvent) {
          UpdateStatusEvent updateStatusEvent = (UpdateStatusEvent) event;
          getView().updateStatus(updateStatusEvent.user, updateStatusEvent.state);
        } else if (event instanceof NewUserEvent) {
          NewUserEvent newUserEvent = (NewUserEvent) event;
          getView().newTeamMember(newUserEvent.teamMember);
        }
      }
    });
  }

  @Override protected void onPresenterDestroy() {
    super.onPresenterDestroy();
    this.useCase.closeConnection();
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

  @Override public void onRequestTeamMemberUpdateStatus(@NonNull Context context,
      @NonNull TeamMember teamMember) {
    navigator.navigateUpdateTeamMemberStatus(context, teamMember);
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
