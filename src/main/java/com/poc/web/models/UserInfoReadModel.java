package com.poc.web.models;

public class UserInfoReadModel extends UserInfoModel {

	private String name;
	
	// Represents REST endpoint URL that should fetch the file from db when get called via the browser
	private String fileUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}		
	
}
