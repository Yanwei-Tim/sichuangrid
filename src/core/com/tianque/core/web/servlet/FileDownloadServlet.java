package com.tianque.core.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.FileUtil;

public class FileDownloadServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(FileDownloadServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		File file = new File(FileUtil.getWebRoot() + File.separator + "download/"
				+ request.getParameter("fileName"));
		response.setCharacterEncoding("GBK");

		if (!file.exists()) {
			response.sendRedirect("/common/error404.jsp");
			return;
		}
		try {
			downloadFile(request, response, file);
		} catch (Exception e) {
			logger.error("错误信息：" + (System.currentTimeMillis() - startTime), e);
		}
	}

	private void downloadFile(HttpServletRequest request, HttpServletResponse response, File file)
			throws IOException, UnsupportedEncodingException, FileNotFoundException {
		OutputStream os = response.getOutputStream();
		long start = 0;
		String range = request.getHeader("range");
		if (range != null) {
			String rg = range.split("=")[1];
			start = Long.parseLong(rg.split("-")[0]);
			response.setStatus(206);
		}
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("Content-Range", "bytes  " + start + "-" + (file.length() - 1) + "/"
				+ file.length());
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(file.getName().getBytes(), "ISO-8859-1") + "\"");
		InputStream is = new FileInputStream(file);
		is.skip(start);
		int len = 0;
		while ((len = is.read()) != -1) {
			os.write(len);
		}
		os.flush();
		os.close();
		is.close();
	}
}