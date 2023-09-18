package com.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.StudentDao;
import com.entity.Student;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Student save(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(student);
		return student;
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Student.class);
		return criteria.list();
	}
}
