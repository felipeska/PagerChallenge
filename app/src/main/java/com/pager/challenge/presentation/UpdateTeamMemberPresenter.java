package com.pager.challenge.presentation;

public interface UpdateTeamMemberPresenter extends Presenter<UpdateTeamMemberPresenter.View> {

  void updateStatus(String username, String status);

  interface View extends Presenter.View {
    void onClose();
    void connectionFailed();
    void startingConnection();
  }
}
