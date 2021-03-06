package com.techedgegroup.accademy.course.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.techedgegroup.accademy.course.datamodel.Student;
import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAllTeachers();
    }

    @Transactional
    public Teacher createTeacher(String teacherName, String teacherSurname, String teacherEmail) {
        Teacher newTeacher = new Teacher();

        newTeacher.setTeacherName(teacherName);
        newTeacher.setTeacherSurname(teacherSurname);
        newTeacher.setTeacherEmail(teacherEmail);

        return teacherRepository.save(newTeacher);
    }

    @Transactional
    public Teacher updateTeacher(Integer id, String teacherName, String teacherSurname, String teacherEmail)
            throws Exception {
        Optional<Teacher> teacherQuery = teacherRepository.findById(id);
        if (!teacherQuery.isPresent()) {
            throw new Exception("Teacher not found");
        }

        Teacher teacher = teacherQuery.get();

        teacher.setTeacherName(teacherName);
        teacher.setTeacherSurname(teacherSurname);
        teacher.setTeacherEmail(teacherEmail);

        teacher.getCourses().size();

        return teacher;
    }

    @Transactional
    public void deleteTeacher(Integer id) throws Exception {
        Optional<Teacher> teacherQuery = teacherRepository.findById(id);
        if (!teacherQuery.isPresent()) {
            throw new Exception("Teacher not found");
        }

        Teacher teacher = teacherQuery.get();

        teacher.getCourses().stream().forEach(course -> { //
            course.getStudents().stream().forEach(student -> { //
                course.getStudents().remove(student);
                student.getCourses().remove(course);
            });
        });

        teacherRepository.deleteById(id);
    }
    
    @Transactional
    public Teacher getTeacher(Integer id) throws Exception {

        Optional<Teacher> teacherQuery = teacherRepository.findById(id);
        if (!teacherQuery.isPresent()) {
            throw new Exception("Teacher not found");
        }

        Teacher teacher = teacherQuery.get(); 
        teacher.getCourses().size();

        return teacher;
    }
}
