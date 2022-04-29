package com.techedgegroup.accademy.course.repository;

import java.util.List;

import com.techedgegroup.accademy.course.datamodel.Course;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @EntityGraph(attributePaths = { "teacher" })
    @Query("select c from Course c")
    List<Course> findAllCourses();

    @Query(value = "SELECT DISTINCT CATEGORY FROM COURSES", nativeQuery = true)
    List<String> findAllCategories();

    // @Modifying(flushAutomatically = true)
    // @Query(nativeQuery = true, value = "DELETE FROM COURSE_STUDENT WHERE COURSE_ID =:id")
    // long deleteCourseStudents(@Param("id") Integer courseId);

}
