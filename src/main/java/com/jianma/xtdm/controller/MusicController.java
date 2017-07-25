package com.jianma.xtdm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianma.xtdm.XTDMController;
import com.jianma.xtdm.exception.XTDMException;
import com.jianma.xtdm.model.Music;
import com.jianma.xtdm.model.ResultModel;
import com.jianma.xtdm.service.MusicService;

@Controller
@RequestMapping(value = "/music")
public class MusicController extends XTDMController {

	@Autowired
	@Qualifier(value = "musicServiceImpl")
	private MusicService musicServiceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultModel createMusic(HttpServletRequest request, HttpServletResponse response, @RequestBody Music music) throws XTDMException{
		resultModel = new ResultModel();
		try {
			music.setCreateTime(new Date());
			int id = musicServiceImpl.createMusic(music);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			resultModel.setObject(id);
			return resultModel;
		} catch (Exception e) {
			throw new XTDMException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ResultModel deleteMusic(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) throws XTDMException{
		resultModel = new ResultModel();
		try{
			musicServiceImpl.deleteMusic(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "删除出错");
		}	
	}
	
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResultModel updateMusic(HttpServletRequest request, HttpServletResponse response,@RequestBody Music music){
		resultModel = new ResultModel();
		try{
			musicServiceImpl.updateMusic(music);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "更新出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getMusicByPage", method = RequestMethod.GET)
	public ResultModel getMusicByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam  int offset, @RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(musicServiceImpl.getMusicByPage(offset, limit));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getMusicKeywordByPage", method = RequestMethod.GET)
	public ResultModel getArticleKeywordByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam  String keyword,
			@RequestParam  int offset, @RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(musicServiceImpl.getMusicKeywordByPage(keyword, offset, limit));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}
	}
}
