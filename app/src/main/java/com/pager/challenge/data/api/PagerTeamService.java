package com.pager.challenge.data.api;

import com.pager.challenge.data.model.TeamMemberDataModel;
import io.reactivex.Observable;
import java.util.List;
import java.util.Map;
import retrofit2.http.GET;

public interface PagerTeamService {

  @GET("/team") Observable<List<TeamMemberDataModel>> team();

  @GET("/roles") Observable<Map<String, String>> roles();
}
