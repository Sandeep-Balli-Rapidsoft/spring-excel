package com.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	public Integer id;

	public String name;

	public Integer age;

	public Double height;

	public BigInteger phone;

	public Boolean isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public BigInteger getPhone() {
		return phone;
	}

	public void setPhone(BigInteger phone) {
		this.phone = phone;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Student(String name, Integer age, Double height, BigInteger phone, Boolean isActive) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.phone = phone;
		this.isActive = isActive;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
}
