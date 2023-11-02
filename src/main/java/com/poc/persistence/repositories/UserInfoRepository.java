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
	public int updatePersonalImage(@Param(value = "nationalId") String nationalId, @Param(value = "personalImage") byte[] personalImage);
	
	@Query(value = "SELECT personal_image "
			+ "FROM user_info "
			+ "WHERE national_id = :nationalId", 
			nativeQuery = true)
	public byte[] loadPersonalImageAsRawData(@Param(value = "nationalId") String nationalId);
	
	@Query(value = "SELECT name, national_id, cell_phone, email, mailing_address "
			+ "FROM user_info "
			+ "WHERE national_id = :nationalId", 
			nativeQuery = true)
	public Object loadUserInfoAsRawData(@Param(value = "nationalId") String nationalId);
	
}
