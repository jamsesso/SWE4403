package swe4403.project.backend;

public interface Iterator<T> {
  void first();
  void next();
  T currentItem();
  Boolean isDone();
}
