package com.jianma.xtdm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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
		sessionFactory.getCurrentSession().save(article);
	}

	@Override
	public void deleteArticle(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Article a  where a.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateArticle(Article article) {
		sessionFactory.getCurrentSession().update(article);
	}

	@Override
	public List<Article> getArticleByPage(int categoryId, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select new Article(id,categoryId,title,abstractContent,label,"
				+ " recommand,thumb,createTime) from Article a order by a.createTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int getCountArticle(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		if (categoryId > 0){
			hql = "select count(a) from Article a where a.categoryId = ?";
		}
		else{
			hql = "select count(a) from Article a ";
		}
		
		Query query = session.createQuery(hql); 
		if (categoryId > 0){
			query.setParameter(0, categoryId);
		}
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public Article getArticleDetailById(int id) {
		// TODO Auto-generated method stub
		return (Article)sessionFactory.getCurrentSession().get(Article.class, id);
	}

	@Override
	public List<Article> getRecommandArticle(int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select new Article(id,categoryId,title,abstractContent,label,"
				+ " recommand,thumb,bgUrl, createTime) from Article a where a.recommand = 1 order by a.createTime desc";
		Query query = session.createQuery(hql);
		query.setMaxResults(limit);
		return query.list();
	}

}
