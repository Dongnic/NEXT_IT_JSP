package com.study.attach.vo;


import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserAttachVO  {
	private int atchNo; /* 첨부파일 번호(PK) */
	private String atchUserId; /* 부모글의 PK */
	private String atchFileName; /* 실제 저장된 파일명 */
	private String atchOriginalName; /* 사용자가 올린 원래 파일명 */
	private long atchFileSize; /* 파일 사이즈   1024,  1024*1024 */
	private String atchFancySize; /* 팬시 사이즈  : 1KB , 1MB   */
	private String atchContentType; /* 컨텐츠 타입 */
	private String atchPath; /* 저장 경로(board/2020) */
	private String atchRegDate; /* 등록일 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public int getAtchNo() {
		return atchNo;
	}
	public void setAtchNo(int atchNo) {
		this.atchNo = atchNo;
	}
	public String getAtchFileName() {
		return atchFileName;
	}
	public void setAtchFileName(String atchFileName) {
		this.atchFileName = atchFileName;
	}
	public String getAtchOriginalName() {
		return atchOriginalName;
	}
	public void setAtchOriginalName(String atchOriginalName) {
		this.atchOriginalName = atchOriginalName;
	}
	public long getAtchFileSize() {
		return atchFileSize;
	}
	public void setAtchFileSize(long atchFileSize) {
		this.atchFileSize = atchFileSize;
	}
	public String getAtchFancySize() {
		return atchFancySize;
	}
	public void setAtchFancySize(String atchFancySize) {
		this.atchFancySize = atchFancySize;
	}
	public String getAtchContentType() {
		return atchContentType;
	}
	public void setAtchContentType(String atchContentType) {
		this.atchContentType = atchContentType;
	}
	public String getAtchPath() {
		return atchPath;
	}
	public void setAtchPath(String atchPath) {
		this.atchPath = atchPath;
	}
	public String getAtchUserId() {
		return atchUserId;
	}
	public void setAtchUserId(String atchUserId) {
		this.atchUserId = atchUserId;
	}
	public String getAtchRegDate() {
		return atchRegDate;
	}
	public void setAtchRegDate(String atchRegDate) {
		this.atchRegDate = atchRegDate;
	}
    	
    //get/set, toString
}