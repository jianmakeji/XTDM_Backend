package com.jianma.xtdm;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jianma.xtdm.exception.ServerException;
import com.jianma.xtdm.exception.XTDMException;
import com.jianma.xtdm.exception.XTDMRedirectException;
import com.jianma.xtdm.model.ResultModel;

public abstract class XTDMController {

	protected ResultModel resultModel = null;

	@ExceptionHandler(XTDMException.class)
	public @ResponseBody ResultModel handleDCException(XTDMException ex) {
		resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	/**
	 * 处理权限异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public @ResponseBody ResultModel handleUnauthorizedException(UnauthorizedException ex) {
		resultModel = new ResultModel();
		resultModel.setResultCode(900);
		resultModel.setMessage("无权限进行操作");
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	
	@ExceptionHandler(AuthorizationException.class)
	public ModelAndView handleAuthorizationException(AuthorizationException ex) {
		ModelAndView modelView = new ModelAndView();
		resultModel = new ResultModel();
		resultModel.setResultCode(911);
		resultModel.setMessage("没有进行身份认证");
		resultModel.setSuccess(false);
		modelView.setViewName("error");
        return modelView;
	}
	
	@ExceptionHandler(XTDMRedirectException.class)
	public ModelAndView handlerDcRedirectException(XTDMRedirectException ex){
		ModelAndView modelView = new ModelAndView();
		resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		modelView.setViewName("error");
        return modelView;
	}
	
	@ExceptionHandler(ServerException.class)
	public ModelAndView handlerException(ServerException ex){
		ModelAndView modelView = new ModelAndView();
		resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		modelView.setViewName("error");
        return modelView;
	}
}
