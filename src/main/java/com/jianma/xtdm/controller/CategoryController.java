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
import com.jianma.xtdm.model.Category;
import com.jianma.xtdm.model.ResultModel;
import com.jianma.xtdm.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends XTDMController {

	@Autowired
	@Qualifier(value = "categoryServiceImpl")
	private CategoryService categoryServiceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultModel createCategory(HttpServletRequest request, HttpServletResponse response, @RequestBody Category category){
		resultModel = new ResultModel();
		try {
			category.setCreateTime(new Date());
			int id = categoryServiceImpl.createCategory(category);
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
	public ResultModel deleteCategory(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		try{
			categoryServiceImpl.deleteCategory(id);
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
	public ResultModel updateCategory(HttpServletRequest request, HttpServletResponse response,@RequestBody Category category){
		resultModel = new ResultModel();
		try{
			categoryServiceImpl.updateCategory(category);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "更新出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getCategoryByPage", method = RequestMethod.GET)
	public ResultModel getCategoryByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam  int offset, @RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(categoryServiceImpl.getCategoryByPage(offset, limit));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new XTDMException(500, "获取数据出错");
		}
	}
}
