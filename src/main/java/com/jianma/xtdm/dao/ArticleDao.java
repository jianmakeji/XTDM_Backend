package com.jianma.xtdm.dao;

import java.util.List;

import com.jianma.xtdm.model.Article;

public interface ArticleDao {
	
	public void createArticle(Article article);
	
	public void deleteArticle(int id);
	
	public void updateArticle(Article article);
	
	public List<Article> getArticleByPage(int categoryId, int offset, int limit);
	
	public int getCountArticle(int categoryId);
	
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
}
