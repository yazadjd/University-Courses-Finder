package com.integradev.unicoursefinder.service.impl;

import com.integradev.unicoursefinder.dto.CourseDTO;
import com.integradev.unicoursefinder.entity.Course;
import com.integradev.unicoursefinder.entity.Hobby;
import com.integradev.unicoursefinder.entity.Location;
import com.integradev.unicoursefinder.repository.CoursesRepository;
import com.integradev.unicoursefinder.repository.HobbiesRepository;
import com.integradev.unicoursefinder.repository.LocationRepository;
import com.integradev.unicoursefinder.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {
    @Autowired
    CoursesRepository coursesRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    HobbiesRepository hobbiesRepository;

    @Override
    @Transactional
    public Set<CourseDTO> getCourses(String selectedCountry, String selectedCity, Set<String> selectedHobbies) {
        List<Course> allCourses = coursesRepository.findAll();
        Set<Course> filteredCourses;
        Set <Location> locations;
        Set<CourseDTO> courses = new HashSet<CourseDTO>();
        Set<Hobby> hobbies = new HashSet<>();


        if (selectedCity.equals("All Cities")) {
            locations = locationRepository.findByCountry(selectedCountry);
        }
        else{
            locations = locationRepository.findByCountryAndCity(selectedCountry, selectedCity);
        }
        if (selectedHobbies != null) {
            hobbies = hobbiesRepository.findByHobbyIn(selectedHobbies);
        }
        if(!hobbies.isEmpty()) {
            Set<Hobby> finalHobbies = hobbies;
            filteredCourses = allCourses.stream().filter(course ->  // Filter for hobbies
                    !Collections.disjoint(finalHobbies, course.getHobbies())).collect(Collectors.toSet());
        }
        else {
            filteredCourses = allCourses.stream().collect(Collectors.toSet());
        }

        if(!selectedCountry.equals("All Countries")) {
            filteredCourses = filteredCourses.stream().filter(course ->
                    !Collections.disjoint(locations, course.getLocations())).collect(Collectors.toSet());
        }
       for(Course course : filteredCourses) {
           CourseDTO courseDTO = new CourseDTO();
           courseDTO.setName(course.getName());
           courseDTO.setDescription(course.getDescription());
           courseDTO.setDuration(course.getDuration());
           courseDTO.setFees(course.getFees());
           courseDTO.setUniName(course.getUniName());
           courses.add(courseDTO);
       }
        return courses;
    }
}
