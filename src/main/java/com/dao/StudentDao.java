package com.dao;

import java.util.List;

import com.entity.Student;

public interface StudentDao {
	
	public Student save(Student student);
	
	public List<Student> getAll();
}
