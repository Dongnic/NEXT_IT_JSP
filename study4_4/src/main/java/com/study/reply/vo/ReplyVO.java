package com.study.reply.vo;

public class ReplyVO {

	private int reNo;                       /* 댓글번호 */
	private String reCategory;              /* 분류(BOARD, PDS, FREE, ...) */
	private int reParentNo;                 /* 부모 번호 */
	private String reMemId;                 /* 작성자ID */
	private String reContent;               /* 댓글 내용 */
	private String reIp;                    /* IP */
	private String reRegDate;               /* 댓글 등록일자 */
	
	private String reMemName;
	
	public String getReMemName() {
		return reMemName;
	}
	public void setReMemName(String reMemName) {
		this.reMemName = reMemName;
	}
	@Override
	public String toString() {
		return "ReplyVO [reNo=" + reNo + ", reCategory=" + reCategory + ", reParentNo=" + reParentNo + ", reMemId="
				+ reMemId + ", reContent=" + reContent + ", reIp=" + reIp + ", reRegDate=" + reRegDate + ", reModDate="
				+ reModDate + "]";
	}
	public int getReNo() {
		return reNo;
	}
	public void setReNo(int reNo) {
		this.reNo = reNo;
	}
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
	public String getReMemId() {
		return reMemId;
	}
	public void setReMemId(String reMemId) {
		this.reMemId = reMemId;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getReIp() {
		return reIp;
	}
	public void setReIp(String reIp) {
		this.reIp = reIp;
	}
	public String getReRegDate() {
		return reRegDate;
	}
	public void setReRegDate(String reRegDate) {
		this.reRegDate = reRegDate;
	}
	public String getReModDate() {
		return reModDate;
	}
	public void setReModDate(String reModDate) {
		this.reModDate = reModDate;
	}
	private String reModDate;               /* 댓글 수정일자 */
	
}
