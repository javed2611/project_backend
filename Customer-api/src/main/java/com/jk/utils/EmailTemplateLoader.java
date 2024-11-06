package com.jk.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplateLoader {

	@Autowired
	private ResourceLoader resourceLoader;

	public String loadTemplete(String filePath) throws IOException {
		Resource resource = resourceLoader.getResource(filePath);
		return new String(Files.readAllBytes(Paths.get(resource.getURI())));
	}

	public String replacePlaceHolders(String template, String name, String url) {
		return template.replace("{{name}}", name).replace("{{resetLink}}", url);
	}

}
