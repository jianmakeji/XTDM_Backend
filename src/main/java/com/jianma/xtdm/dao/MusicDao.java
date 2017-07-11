package com.jianma.xtdm.dao;

import com.jianma.xtdm.model.Music;

public interface MusicDao {

	public void createMusic(Music music);
	
	public void deleteMusic(int id);
	
	public void updateMusic(Music music);
	
	public void getMusicByPage(int offset, int limit);
}
