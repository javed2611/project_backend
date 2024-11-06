package com.jk.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class S3Util {

	@Value("${aws.bucketName}")
	private String bucketName;

	@Value("${aws.region}")
	private String bucketRegion;

	@Autowired
	private AmazonS3 s3;


	public String saveFileInBucket(File file) {
		System.out.println(s3);
		try {
			PutObjectResult putObjectResult = s3.putObject(bucketName, file.getName(), file);
			String invoiceUrl = String.format("https://s3.%s.amazonaws.com/%s/%s", bucketRegion, bucketName,
					file.getName());
			System.out.println(invoiceUrl);
			return invoiceUrl;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
