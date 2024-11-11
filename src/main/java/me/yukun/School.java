package me.yukun;

public class School {

  private final int id;
  private final String name;

  public School(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "School [id=" + id + ", name=" + name + "]";
  }
}
