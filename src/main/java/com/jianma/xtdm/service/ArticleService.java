package com.jianma.xtdm.service;

import com.jianma.xtdm.model.Article;
import com.jianma.xtdm.model.PageObject;

public interface ArticleService {

	public void createArticle(Article article);
	
	public void deleteArticle(int id);
	
	public void updateArticle(Article article);
	
	public PageObject getArticleByPage(int categoryId, int offset, int limit);
}
