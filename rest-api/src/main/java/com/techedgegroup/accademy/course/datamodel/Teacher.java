package com.techedgegroup.accademy.course.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TEACHERS")
@Data
public class Teacher {
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue
	private Integer id;

	@Column(name = "NAME")
	private String teacherName;

	@Column(name = "SURNAME")
	private String teacherSurname;

	@Column(name = "EMAIL")
	private String teacherEmail;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Course> courses = new ArrayList<Course>();
}
