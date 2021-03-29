package com.integradev.unicoursefinder.service;

import com.integradev.unicoursefinder.dto.CourseDTO;

import java.util.Set;

public interface ICourseService {
    public Set<CourseDTO> getCourses(String selectedCountry, String selectedCity, Set<String> selectedHobbies);
}
