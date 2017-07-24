package com.jianma.xtdm.service;

import com.jianma.xtdm.model.Category;
import com.jianma.xtdm.model.PageObject;

public interface CategoryService {

	public int createCategory(Category category);
	
	public void deleteCategory(int id);
	
	public void updateCategory(Category category);
	
	public PageObject getCategoryByPage(int offset, int limit);
}
