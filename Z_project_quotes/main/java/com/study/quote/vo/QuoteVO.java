package com.study.quote.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QuoteVO {
	
	private int quoNo;              /* 글 번호 */         
	private String writer;          /* 글 저자 */        
	private String title;           /* 글 제목 */
	private String text;            /* 글 내용 */
	private String img1;             /* 글 이미지1 */
	private String img2;            /* 글이미지2 */
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public int getQuoNo() {
		return quoNo;
	}
	public void setQuoNo(int quoNo) {
		this.quoNo = quoNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}        
	
}
