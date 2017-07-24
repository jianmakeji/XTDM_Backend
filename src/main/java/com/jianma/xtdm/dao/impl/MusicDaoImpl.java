package com.jianma.xtdm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.xtdm.dao.MusicDao;
import com.jianma.xtdm.model.Music;

@Repository
@Component
@Qualifier(value = "musicDaoImpl")
public class MusicDaoImpl implements MusicDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public int createMusic(Music music) {
		return (int)sessionFactory.getCurrentSession().save(music);
	}

	@Override
	public void deleteMusic(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Music m  where m.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateMusic(Music music) {
		sessionFactory.getCurrentSession().update(music);
	}

	@Override
	public List<Music> getMusicByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Music order by createTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int getCountMusic() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(m) from Music m ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

}
