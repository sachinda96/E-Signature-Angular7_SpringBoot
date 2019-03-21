package com.fileUpload.FileUpload.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fileUpload.FileUpload.dto.TestDto;
import com.fileUpload.FileUpload.dto.fileDto;
import com.fileUpload.FileUpload.service.FileUploadService;

@RestController
@CrossOrigin
@RequestMapping(value="/file")
public class FileUploadConroller {
	
	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping(value="/save")
	public boolean save(@RequestBody fileDto fileDto) throws Exception{
		return fileUploadService.save(fileDto);
	}
	
	@PostMapping(value="/mapImage")
	public float machingImages(@RequestBody TestDto testDto) throws Exception{
		File fileA=new File(testDto.getFile1());
		File fileB=new File(testDto.getFile2());
		
		return fileUploadService.mactchImage(fileA, fileB);
	}
	
	@PostMapping(value="/difImage")
	public String differentImage(@RequestBody TestDto testDto)throws Exception{
		return fileUploadService.getDifferentToImage(testDto);
	}
	
	@PostMapping(value="/get")
	public String getImage(@RequestBody TestDto testDto)throws Exception {
		return fileUploadService.test(testDto);
	}
}
