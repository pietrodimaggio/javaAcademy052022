package com.techedgegroup.accademy.course.mapper;

import java.util.List;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.Student;
import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.restapi.model.CourseOutDTO;
import com.techedgegroup.accademy.course.restapi.model.StudentOutDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mappings({
            @Mapping(target = "teacherId", source = "teacher.id"),
            @Mapping(target = "teacherEmail", source = "teacher.teacherEmail"),
    })
    CourseOutDTO serviceToRest(Course course);

    List<CourseOutDTO> serviceToRest(List<Course> course);

    TeacherOutDTO serviceToRest(Teacher teacher);

    com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO.CourseSummary techerConvertoToRest(Course course);

    List<TeacherOutDTO> teacherServiceToRest(List<Teacher> teacher);

    StudentOutDTO serviceToRest(Student student);

    com.techedgegroup.accademy.course.restapi.model.StudentOutDTO.CourseSummary studentConvertoToRest(Course course);

    List<StudentOutDTO> studentServiceToRest(List<Student> student);
}
