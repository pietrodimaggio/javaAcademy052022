package com.techedgegroup.accademy.course.mapper;

import org.mapstruct.Mapper;

import com.techedgegroup.accademy.course.model.Course;
import com.techedgegroup.accademy.course.model.Teacher;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.CourseInDTODto;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.TeacherInDTODto;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	TeacherInDTODto convertToTeacherInDTODto(Teacher teacher);
	
	CourseInDTODto convertToCourseInDTODto(Course course);
}
