package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CourseOutDTO {
	private Integer id;
	private String courseName;
	private String courseCategory;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date courseDate;

	private Integer teacherId;
	private String teacherEmail;

}
