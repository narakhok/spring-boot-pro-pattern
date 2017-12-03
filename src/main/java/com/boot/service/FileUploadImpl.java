package com.boot.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileUploadImpl implements FileUploadService {
	private String UPLOAD_FOLDER = "/images/";

	@Override
	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println("File :" + file.getOriginalFilename());
		String filename = null;
		try {
			filename= generateFileName(file.getOriginalFilename());
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + filename);
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/resources/" + filename;

	}

	private String generateFileName(String file) {
		String ext = file.substring(file.lastIndexOf("."));

		String fileName = System.currentTimeMillis() + ext;
		return fileName;
	}

}
