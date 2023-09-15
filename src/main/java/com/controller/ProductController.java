package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.helper.Helper;
import com.service.ProductService;
import com.util.Response;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(path = "/product/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		if (Helper.checkExcelFormat(file)) {
			this.productService.save(file);
			return ResponseEntity.status(HttpStatus.OK).body("DB");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Upload Excel Only");
	}
	
	@GetMapping("/all")
	public Response<?> getAll(){
		Response<?> res =  this.productService.getAll();
		return res;
	}
	
	@GetMapping("/export")
	public ResponseEntity<?> exportData() throws IOException {
		String filename = "products.xlsx";
		
		ByteArrayInputStream actualData = this.productService.exportToExcel();
		InputStreamResource file = new InputStreamResource(actualData);
		ResponseEntity<InputStreamResource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename)
		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		.body(file);
		
		return body;
	}
}
