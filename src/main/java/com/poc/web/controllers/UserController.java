package com.poc.web.controllers;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
		
		if (userInfo.getPersonalImage() != null) {
			// Check UserController::downloadImage() to know how the below embedded URL is called
			userInfoReadModel.setFileUrl("http://localhost:8080/v1/users/" + userInfo.getNationalId() + "/images");
		}		
		
		return new ResponseEntity<UserInfoReadModel>(userInfoReadModel, HttpStatus.OK);
	}
	
	// TODO
	@RequestMapping(method = RequestMethod.GET, value = "{nationalId}/images", 
				produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
	public ResponseEntity<Resource> downloadImage(@PathVariable String nationalId) throws IOException {
				
		byte[] personalImage = userService.getPersonalImage(nationalId);
//			OutputStream outputStream = new ByteArrayOutputStream();
//			outputStream.write(personalImage);
//			outputStream.close();
		
//			OutputStream outputStream = response.getOutputStream();
//			outputStream.write(personalImage);
//			outputStream.flush();
//			outputStream.close();
		
		System.out.println(">>> " + personalImage.length);
		
		ByteArrayResource byteArrayResource = new ByteArrayResource(personalImage);

		
		return new ResponseEntity<Resource>(byteArrayResource, HttpStatus.OK);
	}

}
