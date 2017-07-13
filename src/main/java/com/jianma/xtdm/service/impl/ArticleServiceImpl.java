package com.jianma.xtdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.xtdm.dao.ArticleDao;
import com.jianma.xtdm.model.Article;
import com.jianma.xtdm.model.PageObject;
import com.jianma.xtdm.service.ArticleService;

@Service
@Component
@Qualifier(value = "articleServiceImpl")
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	@Qualifier(value = "articleDaoImpl")
	private ArticleDao articleDaoImpl;
	
	@Override
	public void createArticle(Article article) {
		articleDaoImpl.createArticle(article);
	}

	@Override
	public void deleteArticle(int id) {
		articleDaoImpl.deleteArticle(id);
	}

	@Override
	public void updateArticle(Article article) {
		articleDaoImpl.updateArticle(article);
	}

	@Override
	public PageObject getArticleByPage(int categoryId, int offset, int limit) {
		PageObject pageObject = new PageObject();
		List<Article> list = articleDaoImpl.getArticleByPage(categoryId, offset, limit);
		int countPage = articleDaoImpl.getCountArticle(categoryId);
		pageObject.setCount(countPage);
		pageObject.setList(list);
		return pageObject;
	}

	@Override
	public Article getArticleDetailById(int id) {
		return articleDaoImpl.getArticleDetailById(id);
	}

	@Override
	public List<Article> getRecommandArticle(int limit) {
		return articleDaoImpl.getRecommandArticle(limit);
	}

}
