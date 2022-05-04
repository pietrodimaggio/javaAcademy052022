package com.techedgegroup.accademy.course.restapi;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techedgegroup.accademy.course.restapi.model.TeacherInDTO;
import com.techedgegroup.accademy.course.restapi.model.TeacherOutDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Teacher API")
public class TeachersRestController {
	Logger logger = LoggerFactory.getLogger(TeachersRestController.class);

	@Operation(summary = "Get all teachers", description = "Get all teachers")
	@GetMapping(value = "/teacher")
	public List<TeacherOutDTO> getAllTeachers() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
	}

	@Operation(summary = "Add a new teacher", description = "Add a new teacher")
	@PostMapping(value = "/teacher")
	public TeacherOutDTO createTeacher(@Valid @RequestBody TeacherInDTO entity) {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
	}

	@Operation(summary = "Update a teacher", description = "Update a teacher")
	@PutMapping(value = "/teacher/{id}")
	public TeacherOutDTO updateTeacher(@PathVariable("id") Integer id, @Valid @RequestBody TeacherInDTO entity) {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
	}

	@Operation(summary = "Delete a teacher", description = "Delete a teacher")
	@DeleteMapping(value = "/teacher/{id}")
	public String deleteTeacher(@PathVariable("id") Integer id) {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
	}

	@Operation(summary = "Get a teacher", description = "Get a teacher")
	@GetMapping(value = "/teacher/{id}")
	public TeacherOutDTO getStudent(@PathVariable("id") Integer id) {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
	}

}
