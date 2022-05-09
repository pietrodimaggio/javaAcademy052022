package com.techedgegroup.accademy.course.model;

import java.util.List;

import lombok.Data;

@Data
public class TeacherData {
	private Teacher teacher;
	private List<Course> courses;
}
