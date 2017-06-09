package com.pager.challenge.data.api;

import com.pager.challenge.data.cache.RolesCache;
import com.pager.challenge.data.model.TeamMemberDataModel;
import com.pager.challenge.domain.TeamMember;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Retrofit;

public class PagerTeamApiClient {

  private final PagerTeamService apiService;

  private final RolesCache rolesCache;

  public PagerTeamApiClient(Retrofit retrofit, RolesCache rolesCache) {
    this.apiService = retrofit.create(PagerTeamService.class);
    this.rolesCache = rolesCache;
  }

  public Observable<List<TeamMember>> members() {
    return Observable.zip(apiService.team(), apiService.roles(),
        new BiFunction<List<TeamMemberDataModel>, Map<String, String>, List<TeamMember>>() {
          @Override
          public List<TeamMember> apply(@NonNull List<TeamMemberDataModel> teamMemberDataModels,
              @NonNull Map<String, String> roles) throws Exception {

            rolesCache.putAll(roles);

            List<TeamMember> teamMembers = new ArrayList<>(teamMemberDataModels.size());
            for (TeamMemberDataModel teamMemberDataModel : teamMemberDataModels) {
              teamMembers.add(toMember(teamMemberDataModel, roles));
            }
            return teamMembers;
          }
        });
  }

  private TeamMember toMember(TeamMemberDataModel teamMemberDataModel, Map<String, String> role) {
    String roleName = role.get(Integer.toString(teamMemberDataModel.role));
    return new TeamMember(teamMemberDataModel.name, teamMemberDataModel.avatar,
        teamMemberDataModel.github, roleName, teamMemberDataModel.gender,
        teamMemberDataModel.languages, teamMemberDataModel.tags, teamMemberDataModel.location, "");
  }
}
