package com.jianma.xtdm.service;

import java.util.List;

import com.jianma.xtdm.model.Article;
import com.jianma.xtdm.model.PageObject;

public interface ArticleService {

	public int createArticle(Article article);
	
	public void deleteArticle(int id);
	
	public void updateArticle(Article article);
	
	public PageObject getArticleByPage(int categoryId, int offset, int limit);
	
	/**
	 * 根据id加载文章详情
	 * @param id
	 * @return
	 */
	public Article getArticleDetailById(int id);
	
	/**
	 * 获取推荐的文章
	 * @param limit 个数
	 * @return
	 */
	public List<Article> getRecommandArticle(int limit);
	
	public PageObject getArticleKeywordByPage(String keyword, int offset, int limit);
}
