package com.study.common.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.study.attach.vo.AttachVO;

@Component
public class StudyAttachUtils {
	@Value("#{util['file.upload.path']}")
	private String uploadPath;
	@Value("#{util['img.upload.path']}")
	private String imgUploadPath;

	/** 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴 */
	public List<AttachVO> getAttachListByMultiparts(MultipartFile[] multipartFiles, String category, String path)
			throws IOException {
		List<AttachVO> atchList = new ArrayList<AttachVO>();
		for (int i = 0; i < multipartFiles.length; i++) {
			MultipartFile multipart = multipartFiles[i];
			AttachVO vo = this.getAttachByMultipart(multipart, category, path);
			if (vo != null) {
				atchList.add(vo);
			}
		}
		return atchList;
	}

	/** MultipartFile에서 VO 설정 및 업로드 파일 처리 후 리턴, 없는 경우 null */
	public AttachVO getAttachByMultipart(MultipartFile multipart, String category, String path) throws IOException {
		if (!multipart.isEmpty()) {
			String fileName = UUID.randomUUID().toString();
			AttachVO vo = new AttachVO();
			vo.setAtchOriginalName(multipart.getOriginalFilename());
			vo.setAtchFileSize(multipart.getSize());
			vo.setAtchContentType(multipart.getContentType());
			vo.setAtchFileName(fileName);
			vo.setAtchCategory(category);
			vo.setAtchPath(path);
			vo.setAtchFancySize(fancySize(multipart.getSize()));
			String filePath = uploadPath + File.separatorChar + vo.getAtchPath();

			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));
			// 여기서 실제 파일이 저장(regist에서 실행됬다), inputStream을 file로 변환하는 메소드
			// multipart.transferTo(new File(filePath, fileName)); // 비슷한 역할
			return vo;
		} else {
			return null;
		}
	}
	
	// 회원 프로필 사진 1개만 업로드 후 진짜 파일이름만 리턴 
	public String getAttachByProfileImage(MultipartFile multipart, String path, String delfile) throws IOException {
		System.out.println("multipart -------- "+multipart);
		if (!multipart.isEmpty()) {
			String originalName = multipart.getOriginalFilename(); /* 진짜파일이름 */ 
			String fileExtension = originalName.substring(originalName.lastIndexOf(".")); /* 파일 확장자 */
			String fileName = UUID.randomUUID().toString() + fileExtension;        /* 가짜파일이름 */
			String filePath = imgUploadPath + File.separatorChar + path;
			
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));
			
			File deleteFile = new File(filePath, delfile);
			
			
			 if(deleteFile.exists()) {    //삭제하고자 하는 파일이 해당 서버에 존재하면 삭제시킨다
				 deleteFile.delete();
			 }
			// multipart.transferTo(new File(filePath, fileName));
			String memImg = fileName;    /* 저장된 파일이름 */ 
			return memImg;
		} else {
			return null;
		}
	}
	
	
	private DecimalFormat df = new DecimalFormat("#,###.0");

	private String fancySize(long size) {
		if (size < 1024) { // 1k 미만
			return size + " Bytes";
		} else if (size < (1024 * 1024)) { // 1M 미만
			return df.format(size / 1024.0) + " KB";
		} else if (size < (1024 * 1024 * 1024)) { // 1G 미만
			return df.format(size / (1024.0 * 1024.0)) + " MB";
		} else {
			return df.format(size / (1024.0 * 1024.0 * 1024.0)) + " GB";
		}
	}

}