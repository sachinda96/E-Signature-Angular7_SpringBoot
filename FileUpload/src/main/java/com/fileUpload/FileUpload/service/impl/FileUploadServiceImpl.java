package com.fileUpload.FileUpload.service.impl;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.jboss.jandex.Result;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fileUpload.FileUpload.dao.fileDao;
import com.fileUpload.FileUpload.dto.TestDto;
import com.fileUpload.FileUpload.dto.fileDto;
import com.fileUpload.FileUpload.entity.FileEntity;
import com.fileUpload.FileUpload.service.FileUploadService;
import com.fileUpload.FileUpload.util.TwoSimiilerImages;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	
	@Autowired
	private fileDao fileDao;
	@Override
	public boolean save(fileDto fileDto) throws Exception {
		
		String base64Data = fileDto.getUrl().split(",")[1];

		byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
		ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
		BufferedImage image = ImageIO.read(bis);

		File outputFile = new File("/home/sachinda/Downloads/FileUpload/images/"+UUID.randomUUID().toString());
		ImageIO.write(image, "png", outputFile); 
		
		
		// TODO Auto-generated method stub
		FileEntity fileEntity=new FileEntity();
		fileEntity.setId(UUID.randomUUID().toString());
		fileEntity.setUrl(fileDto.getUrl());
		
		//fileDao.save(fileEntity);
		return true;
	}
	
	@Override
	public float mactchImage(File fileA, File fileB) throws Exception {
		// TODO Auto-generated method stub

		    float percentage = 0;
		    try {
		        // take buffer data from both image files //
		        BufferedImage biA = ImageIO.read(fileA);
		        DataBuffer dbA = biA.getData().getDataBuffer();
		        int sizeA = dbA.getSize();
		        BufferedImage biB = ImageIO.read(fileB);
		        DataBuffer dbB = biB.getData().getDataBuffer();
		        int sizeB = dbB.getSize();
		        int count = 0;
		        // compare data-buffer objects //
		        if (sizeA == sizeB) {

		            for (int i = 0; i < sizeA; i++) {

		                if (dbA.getElem(i) == dbB.getElem(i)) {
		                    count = count + 1;
		                }

		            }
		            percentage = (count * 100) / sizeA;
		        } else {
		            System.out.println("Both the images are not of same size");
		        }

		    } catch (Exception e) {
		    	e.printStackTrace();
		        System.out.println("Failed to compare image files ...");
		    }
		    System.out.println("prsantage "+percentage);
		    return percentage;
		
	}

	@Override
	public String getDifferentToImage(TestDto testDto) throws Exception {
		
		
		TwoSimiilerImages cti = new TwoSimiilerImages(testDto.getFile1(), testDto.getFile2());
		cti.setParameters(10, 10);
		cti.compare();
		if (!cti.isIdentic()) {
			System.out.println("no match");
			//CompareTwoImages.saveJPG(cti.getImageResult(), "C:\\Users\\kava\\result.jpg");
		} else {
			System.out.println("match");
		}

		return "work";
	}

	@Override
	public String test(TestDto testDto) throws Exception {
		// TODO Auto-generated method stub
		
		String file1 = testDto.getFile1();
		String file2 = testDto.getFile2();

		// Load the images
		Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
		Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

		try {

			PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 0, 0, -1,
					-1, false);
			PixelGrabber grabImage2Pixels = new PixelGrabber(image2, 0, 0, -1,
					-1, false);

			int[] image1Data = null;

			if (grabImage1Pixels.grabPixels()) {
				int width = grabImage1Pixels.getWidth();
				int height = grabImage1Pixels.getHeight();
				image1Data = new int[width * height];
				image1Data = (int[]) grabImage1Pixels.getPixels();
			}

			int[] image2Data = null;

			if (grabImage2Pixels.grabPixels()) {
				int width = grabImage2Pixels.getWidth();
				int height = grabImage2Pixels.getHeight();
				image2Data = new int[width * height];
				image2Data = (int[]) grabImage2Pixels.getPixels();
			}

			
			HashSet set = new HashSet();

	        for(int j = 0; j < image1Data.length; j++){
	            set.add(image1Data[j]);
	        }

	        int counter = 0;
	        for(int i = 0; i < image2Data.length; i++){
	            if(!set.contains(image2Data[i])){
	                counter++;
	              //  Math.abs(a)
	            }
	        }
	        System.out.println(counter);
	        
			System.out.println("Pixels equal: "
					+ java.util.Arrays.equals(image1Data, image2Data));
			
			
			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		return "Work";
	}

}
