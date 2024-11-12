package me.yukun;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Student {

  private final int id;
  private final String name;
  private final String city;
  private List<Parent> parents;
  private School school;

  public Student(int id, String name, String city) {
    this.id = id;
    this.name = name;
    this.city = city;
  }

  @Autowired
  public void setParents(List<Parent> parents) {
    this.parents = parents;
  }

  @Autowired
  public void setSchool(School school) {
    this.school = school;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", city=" + city + ", parents=" + parents
        + ", school=" + school + "]";
  }
}
