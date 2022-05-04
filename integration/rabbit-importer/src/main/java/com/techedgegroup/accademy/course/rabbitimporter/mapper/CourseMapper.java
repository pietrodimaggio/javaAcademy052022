package com.techedgegroup.accademy.course.rabbitimporter.mapper;

import com.techedgegroup.accademy.course.rabbitimporter.model.Course;
import com.techedgegroup.accademy.course.rabbitimporter.model.Teacher;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.CourseInDTODto;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.TeacherInDTODto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    TeacherInDTODto serviceToDto(Teacher teacherData);

    CourseInDTODto serviceToDto(Course course);
}
