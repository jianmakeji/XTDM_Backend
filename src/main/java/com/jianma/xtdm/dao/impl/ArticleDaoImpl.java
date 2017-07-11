package com.jianma.xtdm.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.xtdm.dao.ArticleDao;
import com.jianma.xtdm.model.Article;

@Repository
@Component
@Qualifier(value = "articleDaoImpl")
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createArticle(Article article) {
		
	}

	@Override
	public void deleteArticle(int id) {
		
	}

	@Override
	public void updateArticle(Article article) {
		
	}

	@Override
	public void getArticleByPage(int categoryId, int offset, int limit) {
		
	}

}
