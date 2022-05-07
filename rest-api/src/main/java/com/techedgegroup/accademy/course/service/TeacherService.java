package com.techedgegroup.accademy.course.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.repository.CourseRepository;
import com.techedgegroup.accademy.course.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private CourseRepository courseRepository;

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
//		return teacherRepository.findAllTeachers();
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
			courseRepository.deleteCourseStudents(course.getId());
		});

//		teacherRepository.deleteStudentCourses(id);

		teacherRepository.deleteById(id);
	}

	@Transactional
	public Teacher getTeacher(Integer id) throws Exception {

		Optional<Teacher> teacherQuery = teacherRepository.findById(id);
		if (!teacherQuery.isPresent()) {
			throw new Exception("Teacher not found");
		}

		Teacher teacher = teacherQuery.get();

		return teacher;
	}
}
