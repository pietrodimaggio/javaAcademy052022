package com.techedgegroup.accademy.course.repository;

import com.techedgegroup.accademy.course.datamodel.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {    

}
