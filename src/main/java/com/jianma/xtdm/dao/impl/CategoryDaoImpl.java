package com.jianma.xtdm.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.xtdm.dao.CategoryDao;
import com.jianma.xtdm.model.Category;

@Repository
@Component
@Qualifier(value = "categoryDaoImpl")
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCategoryByPage(int offset, int limit) {
		// TODO Auto-generated method stub

	}

}
