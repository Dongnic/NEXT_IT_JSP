package com.study.favo.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FavoriteVO {
	private int recNo;      /* 즐겨찾기 번호 */                
	private int boardNo;    /* 해당 글 번호 */                
	private String recId;   /* 회원 아이디  */
	private String recDate; /* 즐겨찾기 날짜  */

	public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}
	public int getRecNo() {
		return recNo;
	}
	public void setRecNo(int recNo) {
		this.recNo = recNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}           
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
