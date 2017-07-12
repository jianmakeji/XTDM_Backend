package com.jianma.xtdm.service;

import com.jianma.xtdm.model.Music;
import com.jianma.xtdm.model.PageObject;

public interface MusicService {

	public void createMusic(Music music);
	
	public void deleteMusic(int id);
	
	public void updateMusic(Music music);
	
	public PageObject getMusicByPage(int offset, int limit);
}
