package com.techedgegroup.accademy.course.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techedgegroup.accademy.course.mapper.CourseMapper;
import com.techedgegroup.accademy.course.model.TeacherData;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.api.CoursesApiApi;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.api.TeacherApiApi;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.CourseInDTODto;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.TeacherInDTODto;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.TeacherOutDTODto;

@Service
public class ImportService {
	Logger logger = LoggerFactory.getLogger(ImportService.class);

	@Autowired
	private TeacherApiApi teacherApiApi;

	@Autowired
	private CoursesApiApi coursesApiApi;

	@Autowired
	private CourseMapper courseMapper;

	@PostConstruct
	private void init() {
		coursesApiApi.getApiClient().setBasePath("http://localhost:8085");
		teacherApiApi.getApiClient().setBasePath("http://localhost:8085");
	}

	@RabbitListener(queues = "corsi")
	public void receiveData(TeacherData data) {
		logger.info("Received data:" + data);

		TeacherInDTODto newTeacher = courseMapper.convertToTeacherInDTODto(data.getTeacher());

		TeacherOutDTODto createdTeacher = teacherApiApi.createTeacher(newTeacher);

		data.getCourses().stream().forEach(course -> {

			CourseInDTODto newCourse = courseMapper.convertToCourseInDTODto(course);

			newCourse.setTeacherId(createdTeacher.getId());

			coursesApiApi.createCourse(newCourse);
		});
	}
}
