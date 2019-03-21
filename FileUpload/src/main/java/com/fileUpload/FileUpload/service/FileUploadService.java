package com.fileUpload.FileUpload.service;

import java.io.File;

import com.fileUpload.FileUpload.dto.TestDto;
import com.fileUpload.FileUpload.dto.fileDto;

public interface FileUploadService {

	public boolean save(fileDto fileDto)throws Exception;
	
	public float mactchImage(File fileA, File fileB)throws  Exception;
	
	public String getDifferentToImage(TestDto testDto)throws Exception;
	
	public String test(TestDto testDto)throws Exception;
}
