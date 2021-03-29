/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

CoursesRepository.java defines an interface that inherits from JPARepository.
It declares the query methods that will be used to retrieve relational data
from the relational DB (Course Table) and store them into corresponding objects.

*/

package com.integradev.unicoursefinder.repository;

import com.integradev.unicoursefinder.entity.Course;
import com.integradev.unicoursefinder.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Integer> {
    Set<Course> findByHobbiesIn(Set<Hobby> hobby);

}
