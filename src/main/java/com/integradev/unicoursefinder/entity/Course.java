/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

Course.java defines the Courses Table entity that contains attributes such as ID,
Name, University Name, Description, Duration and Fees.
It also defines a Many to Many relation with the Location and Hobby Tables as
many locations can link to many courses and vice versa, and similarly for hobbies.

*/

package com.integradev.unicoursefinder.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String description;
  private Double duration;
  private Double fees;
  private String uniName;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
    name = "course_locations",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "location_id"))
  private Set<Location> locations;

  @ManyToMany
  @JoinTable(
    name = "course_hobbies",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "hobby_id"))
  private Set<Hobby> hobbies;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }

  public Double getFees() {
    return fees;
  }

  public void setFees(Double fees) {
    this.fees = fees;
  }

  public Set<Location> getLocations() {
    return locations;
  }

  public void setLocations(Set<Location> locations) {
    this.locations = locations;
  }

  public Set<Hobby> getHobbies() {
    return hobbies;
  }

  public String getUniName() {
    return uniName;
  }

  public void setUniName(String uniName) {
    this.uniName = uniName;
  }

  public void setHobbies(Set<Hobby> hobbies) {
    this.hobbies = hobbies;
  }

}
