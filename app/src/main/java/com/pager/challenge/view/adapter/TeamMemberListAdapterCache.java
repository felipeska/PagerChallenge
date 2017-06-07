package com.pager.challenge.view.adapter;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import com.pager.challenge.domain.TeamMember;
import java.util.ArrayList;
import java.util.List;

public class TeamMemberListAdapterCache {
  private final LongSparseArray<TeamMember> dataSet;
  private final RecyclerView.Adapter adapter;

  public TeamMemberListAdapterCache(RecyclerView.Adapter adapter) {
    this.adapter = adapter;
    this.dataSet = new LongSparseArray<>();
  }

  public void clear() {
    dataSet.clear();
  }

  public void addAll(List<TeamMember> notes) {
    for (TeamMember note : notes) {
      dataSet.put(note.hashCode(), note);
    }
    adapter.notifyDataSetChanged();
  }

  public int size() {
    return dataSet.size();
  }

  public TeamMember get(int position) {
    return dataSet.valueAt(position);
  }

  public void update(List<TeamMember> members) {
    insertNewTeamMembers(members);
    removeTeamMembers(members);
  }

  private void insertNewTeamMembers(List<TeamMember> members) {
    List<TeamMember> membersToBeInserted = new ArrayList<>();
    for (TeamMember member : members) {
      if (dataSet.indexOfKey(member.hashCode()) < 0) {
        membersToBeInserted.add(member);
      }
    }
    for (TeamMember member : membersToBeInserted) {
      dataSet.put(member.hashCode(), member);
      int adapterPosition = dataSet.indexOfValue(member);
      adapter.notifyItemInserted(adapterPosition);
    }
  }

  private void removeTeamMembers(List<TeamMember> members) {
    List<TeamMember> teamMembersToBeDeleted = new ArrayList<>();
    LongSparseArray<TeamMember> membersMap = toMap(members);
    for (int position = 0; position < dataSet.size(); position++) {
      TeamMember cachedTeamMember = dataSet.valueAt(position);
      if (membersMap.indexOfKey(cachedTeamMember.hashCode()) < 0) {
        teamMembersToBeDeleted.add(cachedTeamMember);
      }
    }
    for (TeamMember member : teamMembersToBeDeleted) {
      int adapterPosition = dataSet.indexOfValue(member);
      dataSet.remove(member.hashCode());
      adapter.notifyItemRemoved(adapterPosition);
    }
  }

  private LongSparseArray<TeamMember> toMap(List<TeamMember> members) {
    LongSparseArray result = new LongSparseArray(members.size());
    for (TeamMember member : members) {
      result.put(member.hashCode(), member);
    }
    return result;
  }
}
