package com.jianma.xtdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.xtdm.dao.MusicDao;
import com.jianma.xtdm.model.Article;
import com.jianma.xtdm.model.Category;
import com.jianma.xtdm.model.Music;
import com.jianma.xtdm.model.PageObject;
import com.jianma.xtdm.service.MusicService;

@Service
@Component
@Qualifier(value = "musicServiceImpl")
@Transactional
public class MusicServiceImpl implements MusicService {

	@Autowired
	@Qualifier(value = "musicDaoImpl")
	private  MusicDao musicDaoImpl;
	
	@Override
	public int createMusic(Music music) {
		return musicDaoImpl.createMusic(music);
	}

	@Override
	public void deleteMusic(int id) {
		musicDaoImpl.deleteMusic(id);
	}

	@Override
	public void updateMusic(Music music) {
		musicDaoImpl.updateMusic(music);
	}

	@Override
	public PageObject getMusicByPage(int offset, int limit) {
		PageObject pageObject = new PageObject();
		List<Music> list = musicDaoImpl.getMusicByPage(offset, limit);
		int countPage = musicDaoImpl.getCountMusic();
		pageObject.setCount(countPage);
		pageObject.setList(list);
		return pageObject;
	}

	@Override
	public PageObject getMusicKeywordByPage(String keyword, int offset, int limit) {
		PageObject pageObject = new PageObject();
		List<Music> list = musicDaoImpl.getMusicKeywordByPage(keyword, offset, limit);
		int countPage = musicDaoImpl.getCountMusicByKeyword(keyword);
		pageObject.setCount(countPage);
		pageObject.setList(list);
		return pageObject;
	}

}
