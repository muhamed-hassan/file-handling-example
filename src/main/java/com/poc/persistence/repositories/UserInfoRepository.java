package com.poc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poc.persistence.entities.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	@Modifying
	@Query(value = "UPDATE user_info "
			+ "SET personal_image = :personalImage "
			+ "WHERE national_id = :nationalId", 
			nativeQuery = true)
	public void updatePersonalImage(@Param("personalImage") byte[] personalImage, @Param("nationalId") String nationalId);
	
	public byte[] findPersonalImageByNationalId(String nationalId);
	
}
