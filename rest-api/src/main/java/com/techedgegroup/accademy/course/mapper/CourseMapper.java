package com.techedgegroup.accademy.course.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	TeacherOutDTO serviceToRest(Teacher teacher);

	List<TeacherOutDTO> teacherServiceToRest(List<Teacher> teacher);

}
