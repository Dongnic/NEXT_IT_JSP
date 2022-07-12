package com.study.free.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.valid.Modify;

public class FreeBoardVO {
	
	@Positive(message = "글 번호는 정수여야 한다.", groups = {Modify.class})
	private int boNo;                                 /*글 번호*/
	
	@NotBlank(message = "글 제목은 필수입니다.")
	private String boTitle;                           /*글 제목*/
	
	@NotBlank(message = "글 분류는 필수입니다.")
	@Size(max = 4, min= 4, message = "글 분류는 4글자입니다.")
	private String boCategory;                        /*글 분류 코드*/
	
	@NotBlank(message = "글 작성자는 필수입니다.")
	private String boWriter;                          /*작성자명*/
	
	@NotBlank(message = "비밀번호는 필수입니다.")
	private String boPass;                            /*비밀번호*/
	
	@NotBlank(message = "글 내용 필수입니다.")
	private String boContent;                         /*글 내용*/
	
	private int boHit;                                /*조회수*/
	private String boRegDate;                         /*등록 일자*/
	private String boModDate;                         /*수정 일자*/
	private String boDelYn;                           /*삭제 여부*/
	private String boCategoryNm;                      /*분류 이름 */ 
	//getter/setter도 만들어요
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getBoNo() {
		return boNo;
	}

	public void setBoNo(int boNo) {
		this.boNo = boNo;
	}

	public String getBoTitle() {
		return boTitle;
	}

	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}

	public String getBoCategory() {
		return boCategory;
	}

	public void setBoCategory(String boCategory) {
		this.boCategory = boCategory;
	}

	public String getBoWriter() {
		return boWriter;
	}

	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}

	public String getBoPass() {
		return boPass;
	}

	public void setBoPass(String boPass) {
		this.boPass = boPass;
	}

	public String getBoContent() {
		return boContent;
	}

	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}

	public int getBoHit() {
		return boHit;
	}

	public void setBoHit(int boHit) {
		this.boHit = boHit;
	}

	public String getBoRegDate() {
		return boRegDate;
	}

	public void setBoRegDate(String boRegDate) {
		this.boRegDate = boRegDate;
	}

	public String getBoModDate() {
		return boModDate;
	}

	public void setBoModDate(String boModDate) {
		this.boModDate = boModDate;
	}

	public String getBoDelYn() {
		return boDelYn;
	}

	public void setBoDelYn(String boDelYn) {
		this.boDelYn = boDelYn;
	}

	public String getBoCategoryNm() {
		return boCategoryNm;
	}

	public void setBoCategoryNm(String boCategoryNm) {
		this.boCategoryNm = boCategoryNm;
	}	
	
	
	
	

	
}
