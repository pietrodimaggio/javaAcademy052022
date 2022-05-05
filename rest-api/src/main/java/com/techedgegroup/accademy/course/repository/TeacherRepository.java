package com.techedgegroup.accademy.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techedgegroup.accademy.course.datamodel.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

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

}
