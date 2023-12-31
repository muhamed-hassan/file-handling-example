package com.poc.web.controllers;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poc.domain.UserService;
import com.poc.persistence.entities.UserInfo;
import com.poc.web.models.UserInfoCreateModel;
import com.poc.web.models.UserInfoReadModel;
import com.poc.web.validators.Validator;

@RequestMapping("v1/users")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator; 
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserInfoCreateModel userInfoCreateModel) throws ParseException {
				
		validator.validate(userInfoCreateModel);	
		
		UserInfo userInfo = new UserInfo();
		userInfo.setName(userInfoCreateModel.getName());
		userInfo.setNationalId(userInfoCreateModel.getNationalId());				
		userInfo.setCellPhone(userInfoCreateModel.getCellPhone());
		userInfo.setEmail(userInfoCreateModel.getEmail());
		userInfo.setMailingAddress(userInfoCreateModel.getMailingAddress());
		
		userService.createUser(userInfo);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	// TODO: clean debugging lines later when downloadImage() get completed
	@RequestMapping(method = RequestMethod.POST, value = "{nationalId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadPersonalImage(@PathVariable(value = "nationalId") String nationalId, 
			@RequestParam(value = "personalImage") MultipartFile personalImage) throws IOException {
		
		validator.validate(nationalId);
		
		System.out.println("> nationalId: " + nationalId);
		System.out.println("> personalImage: ");
		System.out.println("  ContentType:" + personalImage.getContentType());
		System.out.println("  Size:" + personalImage.getSize() + " bytes"); // 174591 bytes
		System.out.println("  Read bytes:" + personalImage.getBytes());
		System.out.println("  Read byte[] length:" + personalImage.getBytes().length);
		
		// debugging code to check the file's contents on the disk before storing it in db
		/*
		OutputStream outputStream = new FileOutputStream("D:\\tmp\\" + personalImage.getOriginalFilename());
		outputStream.write(personalImage.getBytes());
		*/
		
		userService.addPersonalImageOfUser(nationalId, personalImage.getBytes());
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "{nationalId}")
	public ResponseEntity<UserInfoReadModel> getUser(@PathVariable String nationalId) {
			
		UserInfo userInfo = userService.getUser(nationalId);
		
		UserInfoReadModel userInfoReadModel = new UserInfoReadModel();
		userInfoReadModel.setName(userInfo.getName());
		userInfoReadModel.setNationalId(userInfo.getNationalId());
		userInfoReadModel.setCellPhone(userInfo.getCellPhone());
		userInfoReadModel.setEmail(userInfo.getEmail());
		userInfoReadModel.setMailingAddress(userInfo.getMailingAddress());
		
		if (userInfo.isImageUploaded()) {
			// Check UserController::downloadImage() to know how the below embedded URL is called
			userInfoReadModel.setFileUrl("http://localhost:8080/v1/users/" + userInfo.getNationalId() + "/images");
		}		
		
		return new ResponseEntity<UserInfoReadModel>(userInfoReadModel, HttpStatus.OK);
	}
	
	// TODO: in progress
	@RequestMapping(method = RequestMethod.GET, value = "{nationalId}/images")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String nationalId) throws IOException {
				
		byte[] personalImage = userService.getPersonalImage(nationalId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(personalImage.length);
		
		
		return new ResponseEntity<byte[]>(personalImage, headers, HttpStatus.OK);
	}

}
