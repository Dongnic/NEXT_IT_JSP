package com.study.member.vo;

import com.study.common.vo.PagingVO;

public class MemberSearchVO  extends PagingVO{
	private String searchWord;
	private String searchType;
	private String searchJob;
	private String searchHobby;
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchJob() {
		return searchJob;
	}
	public void setSearchJob(String searchJob) {
		this.searchJob = searchJob;
	}
	public String getSearchHobby() {
		return searchHobby;
	}
	public void setSearchHobby(String searchHobby) {
		this.searchHobby = searchHobby;
	}
	
	
	
	
}
