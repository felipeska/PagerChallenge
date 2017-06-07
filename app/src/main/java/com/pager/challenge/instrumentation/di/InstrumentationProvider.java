package com.pager.challenge.instrumentation.di;

import com.pager.challenge.instrumentation.ImageDownloader;

public interface InstrumentationProvider {
  ImageDownloader getImageDownloader();
}