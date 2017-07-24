package com.jianma.xtdm.dao;

import java.util.List;

import com.jianma.xtdm.model.Category;

public interface CategoryDao {

	public int createCategory(Category category);
	
	public void deleteCategory(int id);
	
	public void updateCategory(Category category);
	
	public List<Category> getCategoryByPage(int offset, int limit);
	
	public int getCountCategory();
}
