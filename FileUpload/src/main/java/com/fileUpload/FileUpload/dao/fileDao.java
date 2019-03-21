package com.fileUpload.FileUpload.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileUpload.FileUpload.entity.FileEntity;

public interface fileDao extends JpaRepository<FileEntity, String>{

}
