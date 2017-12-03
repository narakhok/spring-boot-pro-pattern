package com.boot.fileuploadController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@GetMapping("/file")
	public String upload() {
		return "upload/FileUpload";
	}

	private String UPLOAD_FOLDER = "/images/";

	@PostMapping("/upload")

	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println("File :" + file.getOriginalFilename());

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + generateFileName(file.getOriginalFilename()));
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "upload/FileUpload";

	}

	private String generateFileName(String file) {
		String ext = file.substring(file.lastIndexOf("."));

		String fileName = System.currentTimeMillis() + ext;
		return fileName;
	}

}
