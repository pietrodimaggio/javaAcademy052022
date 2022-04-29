package com.techedgegroup.accademy.course.repository;

import java.util.List;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.CourseSummary;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @EntityGraph(attributePaths = { "teacher" })
    List<Course> findByCourseCategory(String courseCategory);

    @EntityGraph(attributePaths = { "teacher" })
    @Query("select c from Course c")
    List<Course> findAllCourses();


    @Query(value = "SELECT DISTINCT CATEGORY FROM COURSES", nativeQuery = true)
    List<String> findAllCategories();

    @Query(value = "SELECT new com.techedgegroup.accademy.course.datamodel.CourseSummary(c.id, c.courseName, c.courseCategory, c.courseDate, c.teacher.teacherSurname, size(c.students)) FROM Course c")
    List<CourseSummary> getCourseSummary();

    // @Modifying(flushAutomatically = true)
    // @Query(nativeQuery = true, value = "DELETE FROM COURSE_STUDENT WHERE
    // COURSE_ID =:id")
    // long deleteCourseStudents(@Param("id") Integer courseId);

}
