package com.pager.challenge.presentation;

import com.pager.challenge.domain.event.EventListener;
import com.pager.challenge.domain.event.model.Event;
import com.pager.challenge.domain.interactor.GetTeamMembersUseCase;
import javax.inject.Inject;

public class UpdateTeamMemberPresenterImpl extends BasePresenter<UpdateTeamMemberPresenter.View>
    implements UpdateTeamMemberPresenter {

  private final GetTeamMembersUseCase useCase;

  private EventListener eventListener;

  @Inject public UpdateTeamMemberPresenterImpl(GetTeamMembersUseCase useCase) {
    this.useCase = useCase;
  }

  @Override protected void onPresenterReady() {
    super.onPresenterReady();
    eventListener = new EventListener() {
      @Override public void onEventStart() {
        getView().startingConnection();
      }

      @Override public void onEventFailure(Throwable t) {
        getView().connectionFailed();
      }

      @Override public void onNewEvent(Event event) {
      }
    };
    useCase.registerListener(eventListener);
  }

  @Override protected void onPresenterDestroy() {
    super.onPresenterDestroy();
    useCase.unregisterListener(eventListener);
  }

  @Override public void updateStatus(String username, String status) {
    useCase.updateMemberStatus(username, status);
    getView().onClose();
  }
}
