package com.pager.challenge.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pager.challenge.R;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.instrumentation.ImageDownloader;

public class TeamMemberItemView extends RelativeLayout {

  @BindView(R.id.team_member_avatar) ImageView avatarImageView;
  @BindView(R.id.team_member_name) TextView memberName;
  @BindView(R.id.team_member_github_username) TextView username;
  @BindView(R.id.team_member_role) TextView role;

  public TeamMemberItemView(Context context) {
    super(context);
  }

  public TeamMemberItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TeamMemberItemView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  public void bindTo(TeamMember teamMember, ImageDownloader imageDownloader) {

    final String avatarUrl = teamMember.getAvatar();
    imageDownloader.downloadImage(avatarUrl, avatarImageView);

    memberName.setText(teamMember.getName());
    username.setText(teamMember.getGithub());
    role.setText(teamMember.getRole().getName());
  }
}
