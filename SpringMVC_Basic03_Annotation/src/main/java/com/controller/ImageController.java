package com.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.Photo;

@Controller
@RequestMapping("/image/upload.do")
public class ImageController {

	@RequestMapping(method=RequestMethod.GET)
	public String form() {
		return "image/image";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(Photo photo , HttpServletRequest request) {
		/*
		 	1. Photo DTO 타입으로 데이터 받기
		 	1.1 자동화: name 속성값이 Photo 타입 클래스 memberfield명과 동일
		 	2. public String submit(Photo photo) 하면 내부적으로 일어나는 일
		 	2.1 Photo photo = new Photo();
		 	2.2 자동 주입 [조건: name값과 mamberfield가 같아야 한다]
		 		photo.setName("홍길동");
		 		photo.setAge(50);
		 		
		 		photo.setImage() >> 파일 이름은 자동주입되지 않는다.
		 						 >> 업로드한 파일명을 가져와서 가공작업 해야한다.
		 						 >> setFile(CommonsMultipartFile file) 에다가 자동주입된 것을 추출해서 가공!
		 
		 */
		//System.out.println(photo.toString());
		CommonsMultipartFile imagefile = photo.getFile();
		System.out.println("imagefile.name: " + imagefile.getName());
		System.out.println("imagefile.getContentType: " + imagefile.getContentType());
		System.out.println("imagefile.getOriginalFilename: " + imagefile.getOriginalFilename());
		System.out.println("imagefile.getBytes.length: " + imagefile.getBytes().length);
		
		//POINT : DB에 들어갈 파일명
		photo.setImage(imagefile.getName());
		
		//cos.jar 자동 파일 업로드
		//실제 파일 업로드 구현 
		String filename = imagefile.getOriginalFilename();
		String path = request.getServletContext().getRealPath("/upload"); //배포된 서버 경로
		String fpath = path + "\\" + filename;
		System.out.println(fpath);
		
		FileOutputStream fs = null;
		try {
			 fs = new FileOutputStream(fpath);
			 try {
				fs.write(imagefile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//DB작업 > 파일업로드 완료 가정
		return "image/image";
	}
}
