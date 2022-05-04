package com.techedgegroup.accademy.course.restapi;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Courses API")
public class CoursesRestController {
	Logger logger = LoggerFactory.getLogger(CoursesRestController.class);

	@Operation(summary = "Get all course categories", description = "Get all course categories")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class))) }) })

	@GetMapping(value = "/courseCategory")
	public List<String> getAllCourseCategories() {
		return Collections.emptyList();
	}

	@Operation(summary = "Get a course", description = "Get a course")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CourseOutDTO.class)) }) })
	@GetMapping(value = "/course/{id}")
	public CourseOutDTO getCourse(@PathVariable("id") Integer id) {
		CourseOutDTO item = new CourseOutDTO();

		item.setCourseName("Spring Boot");
		item.setCourseCategory("Java");
		item.setCourseDate(new Date());

		return item;
	}

	@Operation(summary = "Update a course", description = "Update a course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CourseOutDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Teacher not found") })
	@PutMapping(value = "/course/{id}")
	public CourseOutDTO updateCourse(@PathVariable("id") Integer id, @Valid @RequestBody CourseInDTO entity) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Delete a course", description = "Delete a course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "404", description = "Teacher not found") })

	@DeleteMapping(value = "/course/{id}")
	public String deleteCourse(@PathVariable("id") Integer id) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

}
