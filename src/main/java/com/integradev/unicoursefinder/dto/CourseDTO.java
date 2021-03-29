/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

The Course DTO is a Course Data Transfer Object that keeps only those attributes
from the Course Entity/Table that is expected to be sent back to the UI via the
Controller.

*/

package com.integradev.unicoursefinder.dto;

public class CourseDTO {
  private String name;
  private String description;
  private Double duration;
  private Double fees;
  private String uniName;

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

  public String getUniName() {
    return uniName;
  }

  public void setUniName(String uniName) {
    this.uniName = uniName;
  }
}
