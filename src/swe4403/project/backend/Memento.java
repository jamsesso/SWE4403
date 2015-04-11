package swe4403.project.backend;

public interface Memento<T> {
  T getState();
  void setState(T state);
}
