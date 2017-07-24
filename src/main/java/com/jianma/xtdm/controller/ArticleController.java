package com.jianma.xtdm.controller;

import java.util.Date;
import java.util.List;

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
import com.jianma.xtdm.model.Article;
import com.jianma.xtdm.model.ResultModel;
import com.jianma.xtdm.service.ArticleService;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends XTDMController {

	@Autowired
	@Qualifier(value = "articleServiceImpl")
	private ArticleService articleServiceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultModel createArticle(HttpServletRequest request, HttpServletResponse response, @RequestBody Article article){
		resultModel = new ResultModel();
		try {
			article.setCreateTime(new Date());
			int id = articleServiceImpl.createArticle(article);
			System.out.println("======================================:"+id);
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
	public ResultModel deleteArticle(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		try{
			articleServiceImpl.deleteArticle(id);
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
	public ResultModel updateArticle(HttpServletRequest request, HttpServletResponse response,@RequestBody Article article){
		resultModel = new ResultModel();
		try{
			articleServiceImpl.updateArticle(article);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "更新出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getArticleByPage", method = RequestMethod.POST)
	public ResultModel getArticleByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam  int categoryId,
			@RequestParam  int offset, @RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(articleServiceImpl.getArticleByPage(categoryId,offset, limit));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getArticleDetailById/{id}", method = RequestMethod.GET)
	public ResultModel getArticleDetailById(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		try{
			Article article = articleServiceImpl.getArticleDetailById(id);
			resultModel.setObject(article);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}	
	}
	
	@ResponseBody
	@RequestMapping(value="/getRecommandArticle/{limit}", method = RequestMethod.GET)
	public ResultModel getRecommandArticle(HttpServletRequest request, HttpServletResponse response, @PathVariable int limit){
		resultModel = new ResultModel();
		try{
			List<Article> article = articleServiceImpl.getRecommandArticle(limit);
			resultModel.setObject(article);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}	
	}
	
	@ResponseBody
	@RequestMapping(value="/getArticleKeywordByPage", method = RequestMethod.POST)
	public ResultModel getArticleKeywordByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam  String keyword,
			@RequestParam  int offset, @RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(articleServiceImpl.getArticleKeywordByPage(keyword, offset, limit));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}
	}
	
}
