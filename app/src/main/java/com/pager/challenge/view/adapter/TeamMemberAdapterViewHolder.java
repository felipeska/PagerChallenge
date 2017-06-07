package com.pager.challenge.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.instrumentation.ImageDownloader;
import com.pager.challenge.view.layout.TeamMemberItemView;

public class TeamMemberAdapterViewHolder extends RecyclerView.ViewHolder {

  private final TeamMemberItemView itemView;
  private final OnViewHolderClickListener listener;
  private final ImageDownloader imageDownloader;

  public TeamMemberAdapterViewHolder(TeamMemberItemView teamMemberItemView,
      @NonNull OnViewHolderClickListener listener, ImageDownloader imageDownloader) {
    super(teamMemberItemView);
    this.itemView = teamMemberItemView;
    this.listener = listener;
    this.imageDownloader = imageDownloader;
    initializeListeners();
  }

  private void initializeListeners() {
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        int position = getAdapterPosition();
        listener.onViewHolderClick(position);
      }
    });
  }

  public interface OnViewHolderClickListener {
    void onViewHolderClick(int position);
  }

  public void bindTo(TeamMember teamMember) {
    itemView.bindTo(teamMember, imageDownloader);
  }
}
