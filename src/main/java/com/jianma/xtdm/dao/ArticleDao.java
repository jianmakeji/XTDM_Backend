package com.jianma.xtdm.dao;

import java.util.List;

import com.jianma.xtdm.model.Article;

public interface ArticleDao {
	
	public void createArticle(Article article);
	
	public void deleteArticle(int id);
	
	public void updateArticle(Article article);
	
	public List<Article> getArticleByPage(int categoryId, int offset, int limit);
	
	public int getCountArticle(int categoryId);
}
