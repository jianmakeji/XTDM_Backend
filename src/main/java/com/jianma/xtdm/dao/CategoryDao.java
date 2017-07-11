package com.jianma.xtdm.dao;

import com.jianma.xtdm.model.Category;

public interface CategoryDao {

	public void createCategory(Category category);
	
	public void deleteCategory(int id);
	
	public void updateCategory(Category category);
	
	public void getCategoryByPage(int offset, int limit);
}
