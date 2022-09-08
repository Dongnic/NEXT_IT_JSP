package com.study.quote.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.quote.vo.QuoteSearchVO;
import com.study.quote.vo.QuoteVO;

@Mapper
public interface IQuoteDao {
	public int getTotalRowCount(QuoteSearchVO searchVO);
	public int getTotalCount();
	public int getFavoTotalRowCount(String id);
	public List<QuoteVO> getQuoteList(QuoteSearchVO searchVO);
	public List<QuoteVO> getQuoteFavoList(Map<String, Object> map);
	public QuoteVO getQuote(int ranVal);
}
