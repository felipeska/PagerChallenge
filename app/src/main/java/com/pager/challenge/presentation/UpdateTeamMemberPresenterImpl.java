package com.pager.challenge.presentation;

import com.pager.challenge.domain.interactor.GetTeamMembersUseCase;
import javax.inject.Inject;

public class UpdateTeamMemberPresenterImpl extends BasePresenter<UpdateTeamMemberPresenter.View>
    implements UpdateTeamMemberPresenter {

  private final GetTeamMembersUseCase useCase;

  @Inject public UpdateTeamMemberPresenterImpl(GetTeamMembersUseCase useCase) {
    this.useCase = useCase;
  }

  @Override public void updateStatus(String username, String status) {
    useCase.updateMemberStatus(username, status);
    getView().onClose();
  }
}
