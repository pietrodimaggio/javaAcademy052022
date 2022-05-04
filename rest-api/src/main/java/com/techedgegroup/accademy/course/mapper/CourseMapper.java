package com.techedgegroup.accademy.course.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.restapi.model.CourseOutDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	@Mappings({ @Mapping(target = "teacherId", source = "teacher.id"),
			@Mapping(target = "teacherEmail", source = "teacher.teacherEmail"), })
	CourseOutDTO serviceToRest(Course course);

	List<CourseOutDTO> serviceToRest(List<Course> course);

	TeacherOutDTO serviceToRest(Teacher teacher);

	com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO.CourseSummary techerConvertoToRest(Course course);

	List<TeacherOutDTO> teacherServiceToRest(List<Teacher> teacher);

}
