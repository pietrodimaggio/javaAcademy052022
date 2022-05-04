package com.techedgegroup.accademy.course.restapi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.CourseSummary;
import com.techedgegroup.accademy.course.mapper.CourseMapper;
import com.techedgegroup.accademy.course.restapi.model.CourseDataResult;
import com.techedgegroup.accademy.course.restapi.model.CourseInDTO;
import com.techedgegroup.accademy.course.restapi.model.CourseOutDTO;
import com.techedgegroup.accademy.course.restapi.model.StudentOutDTO;
import com.techedgegroup.accademy.course.service.CourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

	@Autowired
	private CourseService coursesService;

	@Autowired
	private CourseMapper courseMapper;

	@Operation(summary = "Get all course categories", description = "Get all course categories")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class))) }) })
	@GetMapping(value = "/courseCategory")
	public List<String> getAllCourseCategories() {
		return coursesService.getAllCourseCategories();
	}

	@Operation(summary = "Get courses by category", description = "Get courses by category")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CourseOutDTO.class))) }) })
	@GetMapping(value = "/courseByCategory")
	public List<CourseOutDTO> getCoursesByCategory(@RequestParam("category") String courseCategory) {
		List<Course> courses = coursesService.getCoursesByCategory(courseCategory);

		return courseMapper.serviceToRest(courses);
	}

	@Operation(summary = "Get all courses", description = "Get all courses")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CourseOutDTO.class))) }) })
	@GetMapping(value = "/course")
	public List<CourseOutDTO> getAllCourses() {
		List<Course> courses = coursesService.getAllCourses();

		return courseMapper.serviceToRest(courses);
	}

	@Operation(summary = "Get courses summary", description = "Get all courses with counters")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CourseDataResult.class))) }) })
	@GetMapping(value = "/courseSummary")
	public List<CourseDataResult> getAllCourseSummary() {
		List<CourseSummary> courses = coursesService.getCourseSummary();

		return courseMapper.summaryServicetoRest(courses);
	}

	@Operation(summary = "Add a new course", description = "Add a new course")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CourseOutDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Teacher not found") })
	@PostMapping(value = "/course")
	public CourseOutDTO createCourse(@Valid @RequestBody CourseInDTO entity) {

		try {
			logger.info("");
			Course newCourse = coursesService.createNewCourse(//
					entity.getTeacherId(), //
					entity.getCourseName(), //
					entity.getCourseCategory(), //
					entity.getCourseDate() //
			);

			return courseMapper.serviceToRest(newCourse);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@Operation(summary = "Get a course", description = "Get a course")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CourseOutDTO.class))) }) 
	@GetMapping(value = "/course/{id}")
	public CourseOutDTO getCourse(@PathVariable("id") Integer id) {
		try {
			Course course = coursesService.getCourse(id);

			return courseMapper.serviceToRest(course);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Update a course", description = "Update a course")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CourseOutDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Teacher not found") })
	@PutMapping(value = "/course/{id}")
	public CourseOutDTO updateCourse(@PathVariable("id") Integer id, @Valid @RequestBody CourseInDTO entity) {
		try {
			Course updatedCourse = coursesService.updateCourse(//
					id, //
					entity.getTeacherId(), //
					entity.getCourseName(), //
					entity.getCourseCategory(), //
					entity.getCourseDate() //
			);

			return courseMapper.serviceToRest(updatedCourse);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Delete a course", description = "Delete a course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "404", description = "Teacher not found") })
	@DeleteMapping(value = "/course/{id}")
	public String deleteCourse(@PathVariable("id") Integer id) {
		try {
			coursesService.deleteCourse(id);

			return "OK";
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
