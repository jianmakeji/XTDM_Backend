package com.baidu.ueditor.upload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.jianma.xtdm.util.UploadOSSUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class BinaryUploader {

	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		if (isAjaxUpload) {
			upload.setHeaderEncoding("UTF-8");
		}

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;

			InputStream is = fileStream.openStream();

			String fileName = new StringBuffer().append(new Date().getTime())
					.append(fileStream.getName().substring(fileStream.getName().indexOf("."))).toString();
			State storageState = null;
			try {
				UploadOSSUtil uploadOSSUtil = new UploadOSSUtil();
				uploadOSSUtil.uploadImgAliyun(is, fileName);
				storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
				storageState.putInfo("state", "SUCCESS");// UEDITOR�Ĺ���:��ΪSUCCESS����ʾstate������
				// ע�⣺�����url�Ƿ��ص�ǰ�˷����ļ���·�����������޸�
				storageState.putInfo("url","http://xtdm.oss-cn-hangzhou.aliyuncs.com/article/" + fileName +"?x-oss-process=style/content-500");
				storageState.putInfo("title", fileName);
				storageState.putInfo("original", fileName);
			} catch (Exception e) {
				
				storageState.putInfo("state", "�ļ��ϴ�ʧ��!");
				storageState.putInfo("url", "");
				storageState.putInfo("title", "");
				storageState.putInfo("original", "");
				// System.out.println("�ļ� "+fileName+" �ϴ�ʧ��!");
			}

			is.close();
			/*
			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}*/

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
