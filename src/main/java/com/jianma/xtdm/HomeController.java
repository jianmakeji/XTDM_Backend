package com.jianma.xtdm;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.jianma.xtdm.util.ConfigInfo;
import com.jianma.xtdm.util.WebRequestUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response) {
		try {
			response.sendRedirect("html/login.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Locale locale, Model model) {
		return "error";
	}
	
	@RequestMapping(value = "/controller", method = RequestMethod.GET)
	public String getControllerFile(Locale locale, Model model) {
		return "controller";
	}
	
	@RequestMapping(value = "/controller", method = RequestMethod.POST)
	public String controller(Locale locale, Model model) {
		return "controller";
	}
	
	@RequestMapping(value = "/uploadKey/{type}", method = RequestMethod.GET)
	public @ResponseBody  Map<String, String> uploadKey(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model, @PathVariable int type) {
		WebRequestUtil.AccrossAreaRequestSet(request, response);
		String endpoint = configInfo.endpoint;
        String accessId = configInfo.accessId;
        String accessKey = configInfo.accessKey;
        String bucket = configInfo.bucket;
        String dir = "";
        if (type == 1){
        	dir = "music/";
        }
        else if(type == 2){
        	dir = "category/";
        }
        else{
        	dir = "others/";
        }
        
        String host = "http://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try { 	
        	long expireTime = 30;
        	long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            //respMap.put("expire", formatISO8601Date(expiration));
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            return respMap;
            
        } catch (Exception e) {
            
            return null;
        }
        
	}
	
}
