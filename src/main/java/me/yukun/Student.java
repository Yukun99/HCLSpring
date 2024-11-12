package me.yukun;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Student {

  private int id;
  private String name;
  private String city;
  private List<Parent> parents;
  private School school;


  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCity(String city) {
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
