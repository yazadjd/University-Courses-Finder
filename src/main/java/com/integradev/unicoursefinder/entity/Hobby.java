package com.integradev.unicoursefinder.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hobbies")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String hobby;

    @ManyToMany(mappedBy = "hobbies")
    private Set<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course) {
        courses.add(course);
        course.getHobbies().add(this);
    }
}

