package com.jianma.xtdm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.xtdm.dao.CategoryDao;
import com.jianma.xtdm.model.Category;

@Repository
@Component
@Qualifier(value = "categoryDaoImpl")
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createCategory(Category category) {
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public void deleteCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Category c  where c.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateCategory(Category category) {
		sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public List<Category> getCategoryByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Category order by createTime asc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int getCountCategory() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(c) from Category c ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

}
