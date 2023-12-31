package com.poc.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.persistence.entities.UserInfo;
import com.poc.persistence.repositories.UserInfoRepository;
import com.poc.web.error_handler.exceptions.NoDataFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Transactional
	public void createUser(UserInfo userInfo) {
		
		userInfoRepository.save(userInfo);
	}
	
	@Transactional
	public void addPersonalImageOfUser(String nationalId, byte[] personalImage) {
		
		int affectedRecords = userInfoRepository.updatePersonalImage(nationalId, personalImage);
		System.out.println(">>> affectedRecords:: " + affectedRecords);
	}
	
	public byte[] getPersonalImage(String nationalId) {
		
		byte[] personalImage = userInfoRepository.loadPersonalImageAsRawData(nationalId);		
		return personalImage;
	}
	
	public UserInfo getUser(String nationalId) {		
		
		Object[] rawData = (Object[]) userInfoRepository.loadUserInfoAsRawData(nationalId);
		if (rawData == null) {
			throw new NoDataFoundException();
		}			

		// name, national_id, cell_phone, email, mailing_address, image_uploaded
		UserInfo userInfo = new UserInfo();
		userInfo.setName((String) rawData[0]);
		userInfo.setNationalId((String) rawData[1]);				
		userInfo.setCellPhone((String) rawData[2]);
		userInfo.setEmail((String) rawData[3]);
		userInfo.setMailingAddress((String) rawData[4]);		
		userInfo.setImageUploaded(((String) rawData[5]).equals("T"));
		
		return userInfo;
	}

}
