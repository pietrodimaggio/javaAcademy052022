package com.techedgegroup.accademy.course.rabbitimporter.service;

import javax.annotation.PostConstruct;

import com.techedgegroup.accademy.course.rabbitimporter.mapper.CourseMapper;
import com.techedgegroup.accademy.course.rabbitimporter.model.TeacherData;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.api.CoursesApiApi;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.api.TeacherApiApi;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.CourseInDTODto;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.TeacherInDTODto;
import com.techedgegroup.accademy.course.rabbitimporter.springbootclient.model.TeacherOutDTODto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImportService {
    Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Value("${webApiUrl}")
    private String webApiUrl;

    @Autowired
    private CoursesApiApi coursesApiApi;

    @Autowired
    private TeacherApiApi teacherApiApi;

    @Autowired
    private CourseMapper courseMapper;

    @PostConstruct
    private void init() {
        coursesApiApi.getApiClient().setBasePath(webApiUrl);
        teacherApiApi.getApiClient().setBasePath(webApiUrl);
    }

    @RabbitListener(queues = "corsi")
    public void addTeacherAndCourse(TeacherData teacherData) throws Exception {

        logger.info("Received message: {}", teacherData);

        TeacherInDTODto teacherInDTO = courseMapper.serviceToDto(teacherData.getTeacher());

        TeacherOutDTODto newTeacher = teacherApiApi.createTeacher(teacherInDTO);

        teacherData.getCourses().stream().forEach(course -> {

            CourseInDTODto courseInDTODto = courseMapper.serviceToDto(course);
            courseInDTODto.setTeacherId(newTeacher.getId());
            coursesApiApi.createCourse(courseInDTODto);

        });
    }
}
