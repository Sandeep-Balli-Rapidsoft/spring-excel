package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Student;
import com.service.StudentService;
import com.util.Response;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student/save")
	public Response<?> save(@RequestBody Student student) {
		Response<?> response = this.studentService.save(student);
		return response;
	}
	
	@GetMapping("/student/export")
	public ResponseEntity<?> exportDataToExcel() throws IOException {
		String filename = "students.xlsx";
		
		ByteArrayInputStream actualData = this.studentService.exportToExcel();
		InputStreamResource file = new InputStreamResource(actualData);
		ResponseEntity<InputStreamResource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename)
		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		.body(file);
		return body;
	}
}
