package com.techedgegroup.accademy.course.restapi;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.techedgegroup.accademy.course.datamodel.Student;
import com.techedgegroup.accademy.course.mapper.CourseMapper;
import com.techedgegroup.accademy.course.restapi.model.StudentInDTO;
import com.techedgegroup.accademy.course.restapi.model.StudentOutDTO;
import com.techedgegroup.accademy.course.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Tag(name = "Student API")
public class StudentsRestController {
	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseMapper courseMapper;

	@Operation(summary = "Get all students", description = "Get all students")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StudentOutDTO.class))) }) })
	@GetMapping(value = "/student")
	public List<StudentOutDTO> getAllStudents() {
		List<Student> students = studentService.getAllStudents();

		return courseMapper.studentServiceToRest(students);
	}
	
	@Operation(summary = "Get a student", description = "Get all students")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful operation", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StudentOutDTO.class))) }) })
	@GetMapping(value = "/student/{id}")
	public StudentOutDTO getStudent(@PathVariable("id") Integer id) {
		try {
			Student student = studentService.getStudent(id);

			return courseMapper.serviceToRest(student);
		
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Add a new student", description = "Add a new student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentOutDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input") })
	@PostMapping(value = "/student")
	public StudentOutDTO createStudent(@Valid @RequestBody StudentInDTO entity) {

		Student newStudent = studentService.createStudent(entity.getStudentName(), entity.getStudentSurname(),
				entity.getStudentEmail());

		return courseMapper.serviceToRest(newStudent);
	}

	@Operation(summary = "Update a student", description = "Update a student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentOutDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input") })
	@PutMapping(value = "/student/{id}")
	public StudentOutDTO updateTeacher(@PathVariable("id") Integer id, @Valid @RequestBody StudentInDTO entity) {
		try {
			Student updatedStudent = studentService.updateStudent(//
					id, //
					entity.getStudentName(), //
					entity.getStudentSurname(), //
					entity.getStudentEmail() //
			);

			return courseMapper.serviceToRest(updatedStudent);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Delete a student", description = "Delete a student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input") })
	@DeleteMapping(value = "/student/{id}")
	public String deleteStudent(@PathVariable("id") Integer id) {
		try {
			studentService.deleteStudent(id);

			return "OK";
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Enrollment in a course", description = "Enroll a student on a course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentOutDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input") })
	@PostMapping(value = "/student/{id}/enroll")
	public StudentOutDTO enrollStudent(@PathVariable("id") Integer id, @PathParam("courseId") Integer courseId) {
		try {
			Student student = studentService.enroll(id, courseId);

			return courseMapper.serviceToRest(student);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Unsubscribe from a course", description = "Disenroll a student from a course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StudentOutDTO.class)) }),
			@ApiResponse(responseCode = "405", description = "Invalid input") })
	@PostMapping(value = "/student/{id}/disenroll")
	public StudentOutDTO disenrollStudent(@PathVariable("id") Integer id, @PathParam("courseId") Integer courseId) {
		try {
			Student student = studentService.disenroll(id, courseId);

			return courseMapper.serviceToRest(student);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
