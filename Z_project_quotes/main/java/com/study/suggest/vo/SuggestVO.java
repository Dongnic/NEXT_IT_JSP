package com.study.suggest.vo;

public class SuggestVO {
	
	private String suggestWord;                 /* 추천 검색어 */

	public String getSuggestWord() {
		return suggestWord;
	}
	public void setSuggestWord(String suggestWord) {
		this.suggestWord = suggestWord;
	}
	@Override
	public String toString() {
		return "SuggestVO [suggestWord=" + suggestWord + "]";
	}
	
}
