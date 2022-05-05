package com.techedgegroup.accademy.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.CourseSummary;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query(value = "select c from Course c join fetch c.teacher where c.courseCategory=:category")
	List<Course> findByCourseCategory(@Param("category") String courseCategory);

	@Query(value = "select c from Course c join fetch c.teacher")
	List<Course> findAllCourses();

	@Query(value = "SELECT DISTINCT CATEGORY FROM COURSES", nativeQuery = true)
	List<String> findAllCategories();

	@Query(value = "SELECT new com.techedgegroup.accademy.course.datamodel.CourseSummary(c.id, c.courseName, c.courseCategory, c.courseDate, c.teacher.teacherSurname, size(c.students)) FROM Course c")
	List<CourseSummary> getCourseSummary();

	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM COURSE_STUDENT WHERE COURSE_ID =:id")
	int deleteCourseStudents(@Param("id") Integer courseId);

}
