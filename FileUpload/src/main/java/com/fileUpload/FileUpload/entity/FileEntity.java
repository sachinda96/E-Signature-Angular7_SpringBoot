package com.fileUpload.FileUpload.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileEntity {

	private String id;
	private String url;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
