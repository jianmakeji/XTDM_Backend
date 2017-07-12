package com.jianma.xtdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.xtdm.dao.CategoryDao;
import com.jianma.xtdm.model.Category;
import com.jianma.xtdm.model.PageObject;
import com.jianma.xtdm.service.CategoryService;

@Service
@Component
@Qualifier(value = "categoryServiceImpl")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	@Qualifier(value = "categoryDaoImpl")
	private  CategoryDao categoryDaoImpl;
	
	@Override
	public void createCategory(Category category) {
		categoryDaoImpl.createCategory(category);
	}

	@Override
	public void deleteCategory(int id) {
		categoryDaoImpl.deleteCategory(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDaoImpl.updateCategory(category);
	}

	@Override
	public PageObject getCategoryByPage(int offset, int limit) {
		PageObject pageObject = new PageObject();
		List<Category> list = categoryDaoImpl.getCategoryByPage(offset, limit);
		int countPage = categoryDaoImpl.getCountCategory();
		pageObject.setCount(countPage);
		pageObject.setList(list);
		return pageObject;
	}

}
