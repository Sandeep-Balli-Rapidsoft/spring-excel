package com.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.ProductDao;
import com.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product save(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(product);
		return product;
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(Product.class);

		return cr.list();
	}

}
