package com.jianma.xtdm.dao.impl;

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
	public void createMusic(Music music) {
		
	}

	@Override
	public void deleteMusic(int id) {
		
	}

	@Override
	public void updateMusic(Music music) {
		
	}

	@Override
	public void getMusicByPage(int offset, int limit) {
		
	}

}
