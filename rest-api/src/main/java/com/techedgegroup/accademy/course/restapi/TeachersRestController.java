package com.techedgegroup.accademy.course.restapi;

import java.util.List;

import javax.validation.Valid;

import com.techedgegroup.accademy.course.datamodel.Student;
import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.mapper.CourseMapper;
import com.techedgegroup.accademy.course.restapi.model.StudentOutDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherInDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO;
import com.techedgegroup.accademy.course.service.TeacherService;

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
@Tag(name = "Teacher API")
public class TeachersRestController {
	Logger logger = LoggerFactory.getLogger(TeachersRestController.class);
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CourseMapper courseMapper;

	@Operation(summary = "Get all teachers", description = "Get all teachers")
	@GetMapping(value = "/teacher")
	public List<TeacherOutDTO> getAllTeachers() {
		List<Teacher> teachers = teacherService.getAllTeachers();

		return courseMapper.teacherServiceToRest(teachers);
	}

	@Operation(summary = "Add a new teacher", description = "Add a new teacher")
	@PostMapping(value = "/teacher")
	public TeacherOutDTO createTeacher(@Valid @RequestBody TeacherInDTO entity) {

		Teacher newTeacher = teacherService.createTeacher(entity.getTeacherName(), entity.getTeacherSurname(),
				entity.getTeacherEmail());

		return courseMapper.serviceToRest(newTeacher);
	}

	@Operation(summary = "Update a teacher", description = "Update a teacher")
	@PutMapping(value = "/teacher/{id}")
	public TeacherOutDTO updateTeacher(@PathVariable("id") Integer id, @Valid @RequestBody TeacherInDTO entity) {
		try {
			Teacher updatedTeacher = teacherService.updateTeacher(//
					id, //
					entity.getTeacherName(), //
					entity.getTeacherSurname(), //
					entity.getTeacherEmail() //
			);

			return courseMapper.serviceToRest(updatedTeacher);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Operation(summary = "Delete a teacher", description = "Delete a teacher")
	@DeleteMapping(value = "/teacher/{id}")
	public String deleteTeacher(@PathVariable("id") Integer id) {
		try {
			teacherService.deleteTeacher(id);

			return "OK";
		} catch (Exception e) {
			logger.error("Errore",e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@Operation(summary = "Get a teacher", description = "Get a teacher")
	@GetMapping(value = "/teacher/{id}")
	public TeacherOutDTO getTeacher(@PathVariable("id") Integer id) {
		try {
			Teacher student = teacherService.getTeacher(id);

			return courseMapper.serviceToRest(student);
		
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
