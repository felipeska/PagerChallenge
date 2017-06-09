package com.pager.challenge.data.cache;

import android.support.v4.util.LruCache;
import java.util.Map;

public class RolesCache {

  private LruCache<String, String> roles = new LruCache<>(10);

  public void putAll(Map<String, String> roles) {
    for (Map.Entry<String, String> pair : roles.entrySet()) {
      this.roles.put(pair.getKey(), pair.getValue());
    }
  }

  public String get(String roleKey) {
    return roles.get(roleKey);
  }
}
