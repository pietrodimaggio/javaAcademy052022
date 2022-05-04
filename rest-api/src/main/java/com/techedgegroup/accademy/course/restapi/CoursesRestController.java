package com.techedgegroup.accademy.course.restapi;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techedgegroup.accademy.course.restapi.model.CourseInDTO;
import com.techedgegroup.accademy.course.restapi.model.CourseOutDTO;

@RestController
public class CoursesRestController {
	Logger logger = LoggerFactory.getLogger(CoursesRestController.class);

	@GetMapping(value = "/courseCategory")
	public List<String> getAllCourseCategories() {
		return Collections.emptyList();
	}

	@GetMapping(value = "/course/{id}")
	public CourseOutDTO getCourse(@PathVariable("id") Integer id) {
		CourseOutDTO item = new CourseOutDTO();
		
		item.setCourseName("Spring Boot");
		item.setCourseCategory("Java");
		item.setCourseDate(new Date());

		return item;
	}

	@PutMapping(value = "/course/{id}")
	public CourseOutDTO updateCourse(@PathVariable("id") Integer id, @RequestBody CourseInDTO entity) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/course/{id}")
	public String deleteCourse(@PathVariable("id") Integer id) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

}
