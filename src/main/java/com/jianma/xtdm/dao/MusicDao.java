package com.jianma.xtdm.dao;

import java.util.List;

import com.jianma.xtdm.model.Music;

public interface MusicDao {

	public int createMusic(Music music);
	
	public void deleteMusic(int id);
	
	public void updateMusic(Music music);
	
	public List<Music> getMusicByPage(int offset, int limit);
	
	public int getCountMusic();
}
