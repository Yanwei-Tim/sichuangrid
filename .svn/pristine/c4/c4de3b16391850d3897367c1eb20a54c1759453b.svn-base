package com.tianque.util;

import java.io.File;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component("templateConfiguration")
public class TemplateConfiguration extends freemarker.template.Configuration {

	private URL tempDir = TemplateConfiguration.class.getResource("/");

	public TemplateConfiguration() throws Exception {
		super();
		setDefaultEncoding("UTF-8");
		setDirectoryForTemplateLoading(new File(tempDir.toString().replace("file:", "")
				+ "tempFiles"));
	}

}
