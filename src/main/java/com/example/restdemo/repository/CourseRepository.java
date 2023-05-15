package com.example.restdemo.repository;

import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c")
    List<Course> getAllCourses();

//    @Query("select c.id, c.name from Course c")
//    List<CourseIdAndNameDTO> getAllCoursesIdAndName();

    Boolean existsByCourseNameIgnoreCase(String name);

    @Query("select c from Course c where c.organisation.id = :organizationId")
    List<Course> findCoursesByOrganizationId(@Param("organizationId") Long organizationId);
}
