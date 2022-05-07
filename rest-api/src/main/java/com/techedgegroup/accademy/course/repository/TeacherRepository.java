package com.techedgegroup.accademy.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techedgegroup.accademy.course.datamodel.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Override
	@EntityGraph(attributePaths = "courses")
	List<Teacher> findAll();
	
//	@Modifying(clearAutomatically = true)
//	@Query(nativeQuery = true, value = "DELETE " //
//			+ "FROM " //
//			+ "	COURSE_STUDENT CS " //
//			+ "JOIN COURSE C ON " //
//			+ "	CS.COURSE_ID = C.ID " //
//			+ "JOIN TEACHER T ON " //
//			+ "	C.TEACHER_ID = T.ID " //
//			+ "WHERE " //
//			+ "	T.ID = :id" //
//	)
//	long deleteStudentCourses(@Param("id") Integer teacherId);

	@Query("select t from Teacher t join fetch t.courses")
	List<Teacher> findAllTeachers();
}
