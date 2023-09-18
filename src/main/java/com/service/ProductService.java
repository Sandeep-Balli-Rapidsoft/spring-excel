package com.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.ProductDao;
import com.entity.Product;
import com.helper.Helper;
import com.util.Response;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public void save(MultipartFile file) throws IOException {
		List<Product> products = Helper.convertExcelToProducts(file.getInputStream());
		for(Product product : products) {
			this.productDao.save(product);
		}
	}
	
	public Response<?> getAll() {
		Response<?> res = new Response<>("Data", this.productDao.getAll());
		return res;
	}
	
	public ByteArrayInputStream exportToExcel() throws IOException {
		List<Product> list = this.productDao.getAll();
		
		ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(list);
		
		return byteArrayInputStream;
	}
	
}
