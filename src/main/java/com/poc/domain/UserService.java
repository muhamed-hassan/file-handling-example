package com.poc.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.persistence.entities.UserInfo;
import com.poc.persistence.repositories.UserInfoRepository;

@Service
public class UserService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Transactional
	public void createUser(UserInfo userInfo) {
		
		userInfoRepository.save(userInfo);
	}
	
	@Transactional
	public void addPersonalImageOfUser() {
		
		// updateModel should hold the below:
		//   nationalId: String
		//   personalImage: byte[]
		
		//userInfoRepository.updatePersonalImage(personalImage, nationalId);
	}
	
	public byte[] getPersonalImageByNationalId(String nationalId) {
		
		byte[] personalImage = userInfoRepository.findPersonalImageByNationalId(nationalId);
		
		return personalImage;
	}

}
