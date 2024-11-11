package me.yukun;

import java.util.List;

public class Student {

  private final int id;
  private final String name;
  private final String city;
  private final List<Parent> parents;
  private final School school;

  public Student(int id, String name, String city, List<Parent> parents, School school) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.parents = parents;
    this.school = school;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", city=" + city + ", parents=" + parents
        + ", school=" + school + "]";
  }
}
