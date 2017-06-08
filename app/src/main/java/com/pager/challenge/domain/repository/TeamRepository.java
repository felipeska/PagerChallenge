package com.pager.challenge.domain.repository;

import com.pager.challenge.domain.TeamMember;
import io.reactivex.Observable;
import java.util.List;

public interface TeamRepository {

  Observable<List<TeamMember>> teamMembers();
}
