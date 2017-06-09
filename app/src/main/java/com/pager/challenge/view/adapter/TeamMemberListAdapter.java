package com.pager.challenge.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.pager.challenge.R;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.instrumentation.ImageDownloader;
import com.pager.challenge.view.layout.TeamMemberItemView;
import java.util.List;

public class TeamMemberListAdapter extends RecyclerView.Adapter<TeamMemberAdapterViewHolder>
    implements TeamMemberAdapterViewHolder.OnViewHolderClickListener {

  private final ImageDownloader imageDownloader;
  private final TeamMemberListAdapterCache cache;
  private OnTeamMemberClickListener listener;

  public TeamMemberListAdapter(ImageDownloader imageDownloader) {
    this.cache = new TeamMemberListAdapterCache(this);
    this.imageDownloader = imageDownloader;
  }

  public void setOnTeamMemberClickListener(@NonNull OnTeamMemberClickListener listener) {
    this.listener = listener;
  }

  public void clear() {
    cache.clear();
  }

  public void addAll(List<TeamMember> members) {
    cache.addAll(members);
  }

  public void update(List<TeamMember> members) {
    cache.update(members);
  }

  public void add(TeamMember teamMember) {
    cache.add(teamMember);
  }

  public void update(String username, String status) {
    cache.update(username, status);
  }

  @Override public TeamMemberAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TeamMemberItemView view = (TeamMemberItemView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.team_member_view, parent, false);
    return new TeamMemberAdapterViewHolder(view, this, imageDownloader);
  }

  @Override public void onBindViewHolder(TeamMemberAdapterViewHolder holder, int position) {
    TeamMember teamMember = cache.get(position);
    holder.bindTo(teamMember);
  }

  @Override public int getItemCount() {
    return cache.size();
  }

  @Override public void onViewHolderClick(int position) {
    if (listener != null) {
      TeamMember teamMember = cache.get(position);
      listener.onTeamMemberClick(teamMember);
    }
  }

  public interface OnTeamMemberClickListener {
    void onTeamMemberClick(@NonNull TeamMember member);
  }
}
