package com.techedgegroup.accademy.course.mapper;

import java.util.List;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.restapi.model.CourseOutDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO.CourseSummary;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mappings({
        @Mapping(target = "teacherId",source = "teacher.id"),
        @Mapping(target = "teacherEmail",source = "teacher.teacherEmail"),
    })
    CourseOutDTO serviceToRest(Course course);

    List<CourseOutDTO> serviceToRest(List<Course> course);

    TeacherOutDTO serviceToRest(Teacher teacher);

    CourseSummary convertoToRest(Course course);

    List<TeacherOutDTO> teacherServiceToRest(List<Teacher> teacher);
}