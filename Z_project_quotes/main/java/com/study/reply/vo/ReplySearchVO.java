package com.study.reply.vo;

import com.study.common.vo.PagingVO;

public class ReplySearchVO extends PagingVO{
	private String reCategory;
	private int reParentNo;
	
	public String getReCategory() {
		return reCategory;
	}
	public void setReCategory(String reCategory) {
		this.reCategory = reCategory;
	}
	public int getReParentNo() {
		return reParentNo;
	}
	public void setReParentNo(int reParentNo) {
		this.reParentNo = reParentNo;
	}
	
}
