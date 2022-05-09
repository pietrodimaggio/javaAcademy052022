package com.techedgegroup.accademy.course.model;

import java.util.Date;

import lombok.Data;

@Data
public class Course {
	private String courseName;
	private String courseCategory;
	private Date courseDate;
}
