package com.integradev.unicoursefinder.repository;

import com.integradev.unicoursefinder.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.Set;

@RepositoryEventHandler
public interface HobbiesRepository extends JpaRepository<Hobby, Integer> {
    @Query(value = "select distinct hobby from hobbies", nativeQuery = true)
    Set<String> findAllHobbies();

    Set<Hobby> findByHobbyIn(Set<String> hobbies);
}
