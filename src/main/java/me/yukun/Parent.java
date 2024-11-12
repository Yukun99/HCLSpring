package me.yukun;

public class Parent {

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
    return "Parent [id=" + id + ", name=" + name + "]";
  }
}
