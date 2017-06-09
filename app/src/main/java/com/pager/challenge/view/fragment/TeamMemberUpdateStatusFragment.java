package com.pager.challenge.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.pager.challenge.R;
import com.pager.challenge.di.ApplicationComponent;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.instrumentation.ImageDownloader;
import com.pager.challenge.presentation.UpdateTeamMemberPresenter;
import com.pager.challenge.view.di.DaggerViewComponent;
import javax.inject.Inject;

import static com.pager.challenge.view.activity.TeamMemberUpdateStatusActivity.EXTRA_TEAM_MEMBER;

public class TeamMemberUpdateStatusFragment extends BaseFragment
    implements UpdateTeamMemberPresenter.View {

  @BindView(R.id.avatar) ImageView avatar;

  @BindView(R.id.new_status) TextInputEditText inputStatus;

  @Inject ImageDownloader imageDownloader;

  @Inject UpdateTeamMemberPresenter presenter;

  @OnClick(R.id.update_status) public void setNewStatus() {
    final String newStatus = inputStatus.getText().toString();
    if (!TextUtils.isEmpty(newStatus)) {
      TeamMember teamMember = getTeamMember();
      final String username = teamMember.getUsername();
      presenter.updateStatus(username, newStatus);
    }
  }

  public static Fragment newInstance(TeamMember teamMember) {
    Bundle arguments = new Bundle(1);
    arguments.putParcelable(EXTRA_TEAM_MEMBER, teamMember);
    Fragment result = new TeamMemberUpdateStatusFragment();
    result.setArguments(arguments);
    return result;
  }

  @Override protected int onRequestLayoutResourceId() {
    return R.layout.fragment_team_member_update_status;
  }

  @Override void onRequestInjection(ApplicationComponent applicationComponent) {
    DaggerViewComponent.builder().applicationComponent(applicationComponent).build().inject(this);
  }

  @Override public void onViewReady() {
    initializeToolbar();
    TeamMember teamMember = getTeamMember();
    getActivity().setTitle(teamMember.getName());
    imageDownloader.downloadImage(teamMember.getAvatar(), avatar);
  }

  @Override protected void onPresenterShouldBeAttached() {
    super.onPresenterShouldBeAttached();
    presenter.onAttach(this);
  }

  @Override protected void onPresenterShouldBeDetached() {
    super.onPresenterShouldBeDetached();
    presenter.onDetach();
  }

  private TeamMember getTeamMember() {
    return getArguments().getParcelable(EXTRA_TEAM_MEMBER);
  }

  private void initializeToolbar() {
    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setHomeButtonEnabled(true);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    boolean result = true;
    switch (item.getItemId()) {
      case android.R.id.home:
        closeView();
        break;
      default:
        result = super.onOptionsItemSelected(item);
        break;
    }
    return result;
  }

  public void closeView() {
    getActivity().finish();
  }

  @Override public void onClose() {
    closeView();
  }
}
