package com.tianque.plugin.weChat.proxy.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.GridProperties;
import com.tianque.plugin.weChat.proxy.util.UTF8PostMethod;

@Service("baseHttpClientService")
@Transactional
public class BaseHttpClientServiceImpl implements BaseHttpClientService {

	private static Logger logger = LoggerFactory.getLogger(BaseHttpClientServiceImpl.class);

	public String postMethod(String url) {
		String host = GridProperties.WECHAT_HOST;
		int port = Integer.valueOf(GridProperties.WECHAT_PORT);
		String requestAction = GridProperties.WECHAT_REQUESTACTION;
		String interfaceUrl = requestAction + "?url=" + url;
		logger.error("web端发送给代理端的Url:" + interfaceUrl);
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(host, port);
		PostMethod post = new UTF8PostMethod(interfaceUrl);
		try {
			client.executeMethod(post);

			String str = post.getResponseBodyAsString();
			return str;
		} catch (Exception e) {
			logger.error("代理错误", e);
			return "代理错误";
		} finally {
			post.releaseConnection();
		}

	}

	@Override
	public Map postMethodAsInputStream(String url) {
		String host = GridProperties.WECHAT_HOST;
		int port = Integer.valueOf(GridProperties.WECHAT_PORT);
		String requestAction = GridProperties.WECHAT_REQUESTACTION;
		String interfaceUrl = requestAction + "?url=" + url;
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(host, port);
		PostMethod post = new UTF8PostMethod(interfaceUrl);
		try {
			client.executeMethod(post);
			InputStream str = post.getResponseBodyAsStream();
			Map map = new HashMap();
			Header header = post.getResponseHeader("Content-disposition");
			if (header != null) {
				String fileName = header.getValue().substring(header.getValue().indexOf("=") + 2,
						header.getValue().length() - 1);
				map.put("fileName", fileName);
				map.put("inputStream", str);
			}
			return map;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
