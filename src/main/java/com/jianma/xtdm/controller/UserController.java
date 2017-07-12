package com.jianma.xtdm.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jianma.xtdm.XTDMController;
import com.jianma.xtdm.exception.ServerException;
import com.jianma.xtdm.exception.XTDMException;
import com.jianma.xtdm.model.ResultModel;
import com.jianma.xtdm.model.User;
import com.jianma.xtdm.model.UserPageModel;
import com.jianma.xtdm.service.UserService;
import com.jianma.xtdm.util.GraphicsUtil;
import com.jianma.xtdm.util.ResponseCodeUtil;


/**
 * 大赛用户信息处理
 * 
 * @author dev
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends XTDMController {

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;


	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResultModel registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws XTDMException{
		resultModel = new ResultModel();
		
			Object sessionRand = request.getSession().getAttribute("rand");
			//前端的值传递过来验证码放在activeCode属性值中
			if (sessionRand != null && sessionRand.toString().equalsIgnoreCase(user.getActivecode())) {
				user.setCreatetime(new Date());
				int result = 0;
				try {
					result = userServiceImpl.createUser(user);
				} catch (Exception e) {
					e.printStackTrace();
					throw new XTDMException(500, "创建出错");
				}
				
				if (result == ResponseCodeUtil.UESR_CREATE_EXIST) {
					resultModel.setResultCode(300);
					resultModel.setSuccess(false);
					resultModel.setMessage("用户已经存在!");
				} else {
					resultModel.setResultCode(200);
					resultModel.setSuccess(true);
				}

				return resultModel;
			} else {
				throw new XTDMException(400, "验证码不正确");
			}
		

	}

	/**
	 * 用户信息更新
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultModel updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
		resultModel = new ResultModel();
		try {
			Subject subject = SecurityUtils.getSubject();
			user.setEmail(subject.getSession().getAttribute("email").toString());
			user.setCreatetime(new Date());
			userServiceImpl.updateUser(user);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new XTDMException(500, "更新出错");
		}
	}

	
	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			// 表明生成的响应是图片
			response.setContentType("image/jpeg");

			Map<String, Object> map = new GraphicsUtil().getGraphics();
			request.getSession().setAttribute("rand", map.get("rand"));
			ImageIO.write((RenderedImage) map.get("image"), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
