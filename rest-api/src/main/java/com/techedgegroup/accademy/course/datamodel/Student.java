package com.techedgegroup.accademy.course.datamodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "STUDENTS")
@Data
public class Student {
    @Id
	@Column(unique = true, nullable = false)
	@GeneratedValue
	private int id;

    @Column(name = "NAME")
    private String studentName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "COURSE_STUDENT",
        joinColumns = @JoinColumn(name="STUDENT_ID"),
        inverseJoinColumns =  @JoinColumn(name="COURSE_ID")
    )
    private Set<Course> courses= new HashSet<Course>();
}
