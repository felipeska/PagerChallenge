package com.pager.challenge.instrumentation;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import com.pager.challenge.R;
import com.pager.challenge.view.transform.CircleStrokeTransformation;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

/**
 * Provide a implementation of an imageDownloader with the powerful picasso lib (Thanks Square).
 **/
public class PicassoImageDownloader implements ImageDownloader {

  private Context context;

  private final CircleStrokeTransformation avatarTransformation;

  @Inject public PicassoImageDownloader(Application application) {
    this.context = application;
    this.avatarTransformation = new CircleStrokeTransformation(context,
        ContextCompat.getColor(context, R.color.content_placeholder), 1);
  }

  @Override public void downloadImage(@NonNull String imageUrl, ImageView view) {
    if (imageUrl.isEmpty()) return;
    Picasso.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.avatar_placeholder)
        .transform(avatarTransformation)
        .into(view);
  }
}
