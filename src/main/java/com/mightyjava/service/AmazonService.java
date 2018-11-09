package com.mightyjava.service;

import org.springframework.web.multipart.MultipartFile;

import com.mightyjava.model.User;

public interface AmazonService {
	String uploadFile(MultipartFile multipartFile, User user);
}
