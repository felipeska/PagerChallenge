package com.pager.challenge.presentation;

import android.support.annotation.NonNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<VIEW extends Presenter.View> implements Presenter<VIEW> {
  private boolean isViewAttached;
  private VIEW view;

  public final boolean isViewAttached() {
    return isViewAttached;
  }

  @Override public final VIEW getView() {
    return this.view;
  }

  @Override public void onAttach(@NonNull VIEW view) {
    this.view = view;
    this.isViewAttached = true;
    onPresenterReady();
  }

  @Override public void onDetach() {
    this.view = buildDynamicProxyForView();
    this.isViewAttached = false;
    onPresenterDestroy();
  }

  protected void onPresenterDestroy() {
  }

  protected void onPresenterReady() {
  }

  private VIEW buildDynamicProxyForView() {
    final Class<?> viewClass = getViewInterfaceClass();
    InvocationHandler emptyHandler = new InvocationHandler() {
      @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
      }
    };
    ClassLoader classLoader = viewClass.getClassLoader();
    Class[] interfaces = new Class[1];
    interfaces[0] = viewClass;
    return (VIEW) Proxy.newProxyInstance(classLoader, interfaces, emptyHandler);
  }

  private Class<?> getViewInterfaceClass() {
    Class<?> interfaceClass = null;
    Class<?>[] interfaces = this.view.getClass().getInterfaces();
    for (Class<?> interfaceCandidate : interfaces) {
      if (View.class.isAssignableFrom(interfaceCandidate)) {
        interfaceClass = interfaceCandidate;
      }
    }
    return interfaceClass;
  }
}
