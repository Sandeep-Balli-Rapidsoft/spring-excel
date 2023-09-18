package com.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StudentDao;
import com.entity.Student;
import com.helper.Helper;
import com.util.Response;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	public Response<?> save(Student student) {
		this.studentDao.save(student);
		return new Response<>("success", student);
	}
	
	public ByteArrayInputStream exportToExcel() throws IOException {
		List<Student> list = this.studentDao.getAll();
		
		ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(list);
		return byteArrayInputStream;
	}
}
