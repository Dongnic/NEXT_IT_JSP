package com.study.quote.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vo.PagingVO;

public class QuoteSearchVO extends PagingVO {
	
	private String searchType;
	private String searchWord;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
