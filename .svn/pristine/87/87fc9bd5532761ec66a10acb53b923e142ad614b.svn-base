package com.tianque.plugin.weChat.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class Test {
	
	private static Test test;
	public static Test getIntance(){
		if(test==null)
			synchronized (Test.class) {
				if(test==null)
				test=new Test();
			
			}
		return test;
	}
	public void t(){}
	
	
	
	private static String getContentType(String fileExt) {
	
		String contentType = "text/html; charset=utf-8";
		if (".jpg".equals(fileExt))
			contentType = "image/jpeg";
		else if (".JPG".equals(fileExt))
			contentType = "image/jpeg";
		else if (".amr".equals(fileExt))
			contentType = "application/octet-stream";
		else if (".mp3".equals(fileExt))
			contentType = "application/octet-stream";

		return contentType;
	}

	// 上传文件时定义数据分隔符
	private static final String boundary = "WebKitFormBoundaryfDsTyJ8xqC8uQpON";

	/**
	 * 发起http请求 用于上传文件
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param mediaUrlStr
	 * @return
	 */
	public static JSONObject httpsRequestForUpload(String requestUrl, String requestMethod,
			String mediaUrlStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			/** 设置请求方式（GET/POST） */
			httpUrlConn.setRequestMethod(requestMethod);
			httpUrlConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=----"
					+ boundary);
			OutputStream outputStream = httpUrlConn.getOutputStream();
			URL mediaUrl = new URL(mediaUrlStr);
			HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
			mediaConn.setDoOutput(true);
			mediaConn.setRequestMethod(Constants.GET);
			// 请求头中获取内容类型
			String fileExt = mediaUrlStr.substring(mediaUrlStr.lastIndexOf("."));
			String contentType = getContentType(fileExt);
			String fileName = mediaUrlStr.substring(mediaUrlStr.lastIndexOf("/") + 1,
					mediaUrlStr.lastIndexOf("."));
			System.out.println(contentType);
			// 请求开始
			outputStream.write(("------" + boundary + "\r\n").getBytes());
			outputStream.write(String.format(
					"Content-Disposition:form-data;name=\"media\";filename=\"%s%s\"\r\n", fileName,
					fileExt).getBytes());
			outputStream.write(String.format("Content-Type:%s\r\n\r\n", contentType).getBytes());
			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(mediaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n------" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			mediaConn.disconnect();

			/** 将返回的输入流转换成字符串 */
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			/** 释放资源 */
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static void main(String[] args) {
//		JSONObject jsonObject = httpsRequestForUpload(
//				"http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=P7KQofGnO093ZXAKKa67szLE0pcE_IIz0f0tl9g6yDAh4wxmzJLf73bEFr16KkOr&type=voice",
//				"POST", "http://localhost:8090/uploadFile/tmp/28/bb.amr");
//		System.out.println(jsonObject.toString());
		//System.out.println(Math.pow(2, 2));
	}

}
