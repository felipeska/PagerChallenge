package com.pager.challenge.domain.repository;

import com.pager.challenge.data.socket.SocketService;
import com.pager.challenge.domain.TeamMember;
import io.reactivex.Observable;
import java.util.List;

public interface TeamRepository extends SocketService {

  Observable<List<TeamMember>> teamMembers();
}
