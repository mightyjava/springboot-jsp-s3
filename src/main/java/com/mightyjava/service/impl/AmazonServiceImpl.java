package com.mightyjava.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mightyjava.model.User;
import com.mightyjava.service.AmazonService;
import com.mightyjava.service.UserService;
import com.mightyjava.utils.ConstantUtils;

@Service
public class AmazonServiceImpl implements AmazonService {

	private AmazonS3 s3Client;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	private void initializeAmazon() {
		this.s3Client = new AmazonS3Client(new BasicAWSCredentials(ConstantUtils.ACCESS_KEY, ConstantUtils.SECRET_KEY));
	}

	@Override
	public String uploadFile(MultipartFile multipartFile, User user) {
		String fileUrl = "";
		JSONObject jsonObject = new JSONObject();
		try {
			File file = convertMultipartToFile(multipartFile);
			String fileName = new Date().getTime()+"-"+multipartFile.getOriginalFilename().replace(" ", "_");
			fileUrl = ConstantUtils.END_POINT_URL+"/"+ConstantUtils.BUCKET_NAME+"/"+fileName;
			s3Client.putObject(new PutObjectRequest(ConstantUtils.BUCKET_NAME, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
			user.setProfilePhoto(fileUrl);
			userService.addUser(user);
			jsonObject.put("status", "success");
			jsonObject.put("imageUrl", fileUrl);
			jsonObject.put("message", "File Uploaded Successfully.");
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	private File convertMultipartToFile(MultipartFile file) throws IOException {
		File convertFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertFile);
		fos.write(file.getBytes());
		fos.close();
		return convertFile;
	}
}
