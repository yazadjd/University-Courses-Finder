/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

Purpose: Interface for Course Service.

Defines the function declarations that are implemented in Course Service.

*/

package com.integradev.unicoursefinder.service;

import com.integradev.unicoursefinder.dto.CourseDTO;

import java.util.Set;

public interface ICourseService {
  Set<CourseDTO> getCourses(String selectedCountry, String selectedCity, Set<String> selectedHobbies);
}
