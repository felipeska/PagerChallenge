package com.pager.challenge.view.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindDimen;
import butterknife.BindView;
import com.pager.challenge.R;
import com.pager.challenge.di.ApplicationComponent;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.instrumentation.ImageDownloader;
import com.pager.challenge.presentation.TeamMemberListPresenter;
import com.pager.challenge.view.adapter.TeamMemberListAdapter;
import com.pager.challenge.view.behavior.loading.LoadingBehavior;
import com.pager.challenge.view.di.DaggerViewComponent;
import com.pager.challenge.view.layout.DividerItemDecoration;
import java.util.List;
import javax.inject.Inject;

import static com.pager.challenge.view.layout.DividerItemDecoration.VERTICAL_LIST;

public class TeamMemberListFragment extends BaseFragment implements TeamMemberListPresenter.View {

  @BindView(R.id.team_member_list) RecyclerView teamMemberListView;

  @BindDimen(R.dimen.trending_divider_padding_start) float dividerPaddingStart;

  private LoadingBehavior loadingBehavior;

  private TeamMemberListAdapter adapter;

  @Inject TeamMemberListPresenter presenter;

  @Inject ImageDownloader imageDownloader;

  public static Fragment newInstance() {
    return new TeamMemberListFragment();
  }

  @Override protected int onRequestLayoutResourceId() {
    return R.layout.fragment_team_member_list;
  }

  @Override protected void onPresenterShouldBeAttached() {
    super.onPresenterShouldBeAttached();
    presenter.onAttach(this);
  }

  @Override protected void onPresenterShouldBeDetached() {
    super.onPresenterShouldBeDetached();
    presenter.onDetach();
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onViewReady() {
    setRetainInstance(true);
    initializeBehaviors();
    configureAdapterForList();
    presenter.onRequestTeamMembers();
  }

  private void configureAdapterForList() {
    if (adapter == null) {
      adapter = new TeamMemberListAdapter(this.imageDownloader);
    }

    adapter.setOnTeamMemberClickListener(new TeamMemberListAdapter.OnTeamMemberClickListener() {
      @Override public void onTeamMemberClick(@NonNull TeamMember member) {
        presenter.onRequestTeamMemberUpdateStatus(getActivity(), member);
      }
    });
    teamMemberListView.setHasFixedSize(true);
    teamMemberListView.setLayoutManager(new LinearLayoutManager(getContext()));
    teamMemberListView.addItemDecoration(
        new DividerItemDecoration(getContext(), VERTICAL_LIST, dividerPaddingStart, false));
    teamMemberListView.setAdapter(adapter);
  }

  private void initializeBehaviors() {
    if (isAdded()) {
      initializeLoadingBehavior();
    }
  }

  private void initializeLoadingBehavior() {
    loadingBehavior = new LoadingBehavior();
    loadingBehavior.inject(getActivity());
  }

  @Override void onRequestInjection(ApplicationComponent applicationComponent) {
    DaggerViewComponent.builder().applicationComponent(applicationComponent).build().inject(this);
  }

  @Override public void renderTeamMemberList(List<TeamMember> members) {
    adapter.clear();
    adapter.addAll(members);
  }

  @Override public void updateTeamMemberList(List<TeamMember> members) {
    adapter.update(members);
  }

  @Override public void newTeamMember(TeamMember teamMember) {
    adapter.add(teamMember);
  }

  @Override public void showLoading() {
    if (isAdded()) {
      loadingBehavior.showLoading();
    }
  }

  @Override public void hideLoading() {
    if (isAdded()) {
      loadingBehavior.hideLoading();
    }
  }

  @Override public void updateStatus(String member, String status) {
    adapter.update(member, status);
  }

  @Override public void connectionFailed() {
    Snackbar.make(teamMemberListView, R.string.error_message, Snackbar.LENGTH_LONG).show();
  }

  @Override public void startingConnection() {
    Snackbar.make(teamMemberListView, R.string.connecting_message, Snackbar.LENGTH_LONG).show();
  }
}
