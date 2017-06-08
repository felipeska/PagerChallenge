package com.pager.challenge.view.layout;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pager.challenge.R;
import com.pager.challenge.domain.TeamMember;
import com.pager.challenge.instrumentation.ImageDownloader;
import com.vdurmont.emoji.EmojiManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TeamMemberItemView extends RelativeLayout {

  @BindView(R.id.team_member_avatar) ImageView avatarImageView;
  @BindView(R.id.team_member_name) TextView memberName;
  @BindView(R.id.team_member_github_username) TextView username;
  @BindView(R.id.team_member_role) TextView role;
  @BindView(R.id.team_member_skills) TextView skills;
  @BindView(R.id.team_member_languages) TextView languages;
  @BindView(R.id.team_member_status) TextView status;

  Resources resources = getResources();

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

    memberName.setText(nameWithLocation(teamMember.getName(), teamMember.getLocation()));

    username.setText(String.format("@%s", teamMember.getUsername()));

    role.setText(teamMember.getRole());

    String separator = ", ";

    skills.setText(String.format(resources.getString(R.string.label_skills),
        TextUtils.join(separator, teamMember.getTags())));

    languages.setText(String.format(resources.getString(R.string.label_languages),
        TextUtils.join(separator, getDisplayLanguages(teamMember.getLanguages()))));

    String currentStatus =
        TextUtils.isEmpty(teamMember.getStatus()) ? resources.getString(R.string.not_status)
            : String.format(resources.getString(R.string.label_status), teamMember.getStatus());

    status.setText(currentStatus);
  }

  private ArrayList<String> getDisplayLanguages(List<String> languages) {
    ArrayList<String> displayLanguages = new ArrayList<>();
    for (String language : languages) {
      displayLanguages.add(new Locale(language).getDisplayLanguage(Locale.US));
    }
    return displayLanguages;
  }

  private String nameWithLocation(String name, String location) {
    return name + " " + EmojiManager.getForAlias(location).getUnicode();
  }
}
