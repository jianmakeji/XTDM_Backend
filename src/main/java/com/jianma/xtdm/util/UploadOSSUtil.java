package com.jianma.xtdm.util;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aliyun.oss.OSSClient;

public class UploadOSSUtil {
	
	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	public UploadOSSUtil() {
	}

	public void uploadImgAliyun(InputStream inputStream, String fileName) throws FileNotFoundException {
		
		String accesskeyId = configInfo.accessId;
		String accessKeySecret = configInfo.accessKey;
		String endpoint = configInfo.endpoint;
		String bucketName = configInfo.bucket;
		
		OSSClient client = new OSSClient(endpoint, accesskeyId, accessKeySecret);
		client.putObject(bucketName, "article/" + fileName, inputStream);
		client.shutdown();
		
	}
}
