package com.jianma.xtdm.dao;

import com.jianma.xtdm.model.Article;

public interface ArticleDao {
	
	public void createArticle(Article article);
	
	public void deleteArticle(int id);
	
	public void updateArticle(Article article);
	
	public void getArticleByPage(int categoryId, int offset, int limit);
}
