package com.techedgegroup.accademy.course.service;

import java.util.List;
import java.util.Optional;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.Student;
import com.techedgegroup.accademy.course.repository.CourseRepository;
import com.techedgegroup.accademy.course.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Transactional
    public Student createStudent(String studentName, String studentSurname, String email) {

        Student newStudent = new Student();

        newStudent.setStudentName(studentName);
        newStudent.setStudentSurname(studentSurname);
        newStudent.setStudentEmail(email);

        studentRepository.save(newStudent);

        return newStudent;
    }

    @Transactional
    public Student updateStudent(Integer id, String studentName, String studentSurname, String email) throws Exception {

        Optional<Student> studentQuery = studentRepository.findById(id);
        if (!studentQuery.isPresent()) {
            throw new Exception("Student not found");
        }

        Student student = studentQuery.get();

        student.setStudentName(studentName);
        student.setStudentSurname(studentSurname);
        student.setStudentEmail(email);

        student.getCourses().size();

        return student;
    }

    @Transactional
    public void deleteStudent(Integer id) throws Exception {
        Optional<Student> studentQuery = studentRepository.findById(id);
        if (!studentQuery.isPresent()) {
            throw new Exception("Student not found");
        }

        Student student = studentQuery.get();

        student.getCourses().stream().forEach(course -> { //
            course.getStudents().remove(student);
            student.getCourses().remove(course);
        });

        // studentRepository.deleteStudentCourses(id);

        studentRepository.deleteById(id);
    }

    @Transactional
    public Student enroll(Integer id, Integer courseId) throws Exception {
        Optional<Student> studentQuery = studentRepository.findById(id);
        if (!studentQuery.isPresent()) {
            throw new Exception("Student not found");
        }

        Optional<Course> courseQuery = courseRepository.findById(courseId);
        if (!courseQuery.isPresent()) {
            throw new Exception("Course not found");
        }

        Student student = studentQuery.get();
        Course course = courseQuery.get();

        student.getCourses().add(course);
        course.getStudents().add(student);

        return student;
    }

    @Transactional
    public Student disenroll(Integer id, Integer courseId) throws Exception {
        Optional<Student> studentQuery = studentRepository.findById(id);
        if (!studentQuery.isPresent()) {
            throw new Exception("Student not found");
        }

        Optional<Course> courseQuery = courseRepository.findById(courseId);
        if (!courseQuery.isPresent()) {
            throw new Exception("Course not found");
        }

        Student student = studentQuery.get();
        Course course = courseQuery.get();

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        return student;
    }
}
