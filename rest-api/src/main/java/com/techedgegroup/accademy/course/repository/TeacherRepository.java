package com.techedgegroup.accademy.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techedgegroup.accademy.course.datamodel.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
