package me.yukun;

public class School {

  private int id;
  private String name;

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "School [id=" + id + ", name=" + name + "]";
  }
}
