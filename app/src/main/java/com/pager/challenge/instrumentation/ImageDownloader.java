package com.pager.challenge.instrumentation;

import android.support.annotation.NonNull;
import android.widget.ImageView;

public interface ImageDownloader {
  void downloadImage(@NonNull String imageUrl, ImageView view);
}
