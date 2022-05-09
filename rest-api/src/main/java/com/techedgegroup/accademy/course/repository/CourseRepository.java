package com.techedgegroup.accademy.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.CourseSummary;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	@EntityGraph(attributePaths = { "teacher" })
	List<Course> findByCourseCategory(String courseCategory);

	@Override
	@EntityGraph(attributePaths = { "teacher" })
	List<Course> findAll();

	@Query(value = "SELECT DISTINCT CATEGORY FROM COURSES", nativeQuery = true)
	List<String> findAllCategories();

	 @Query(value = "SELECT new com.techedgegroup.accademy.course.datamodel.CourseSummary(c.id, c.courseName, c.courseCategory, c.courseDate, c.teacher.teacherSurname, size(c.students)) FROM Course c")
	    List<CourseSummary> getCourseSummary();

	@Modifying
	@Query(value = "DELETE FROM COURSE_STUDENT WHERE COURSE_ID=:idCorso", nativeQuery = true)
	void deleteCourseStudents(@Param("idCorso") Integer id);

}
