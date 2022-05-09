package com.techedgegroup.accademy.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techedgegroup.accademy.course.datamodel.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Override
	@EntityGraph(attributePaths = "courses")
	List<Teacher> findAll();

	@Modifying
	@Query(value = "DELETE  \r\n"
			+ "	CS\r\n"
			+ "    FROM\r\n"
			+ "        course_student  CS \r\n"
			+ "    LEFT JOIN\r\n"
			+ "        courses  C \r\n"
			+ "            ON C.ID=CS.COURSE_ID \r\n"
			+ "    LEFT JOIN\r\n"
			+ "        teachers  T \r\n"
			+ "            ON T.ID=C.TEACHER_ID \r\n"
			+ "    WHERE\r\n"
			+ "        T.ID=:idTeacher", nativeQuery = true)
	void deleteAllCourses(@Param("idTeacher") Integer id);
}
