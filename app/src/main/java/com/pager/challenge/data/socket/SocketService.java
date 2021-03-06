package com.pager.challenge.data.socket;

import android.support.annotation.NonNull;
import com.pager.challenge.domain.event.EventListener;

public interface SocketService {

  void registerListenerEvents(@NonNull EventListener eventListener);

  void unregisterListenerEvents(@NonNull EventListener eventListener);

  void sendUpdateStatus(String username, String state);

  void close();
}
