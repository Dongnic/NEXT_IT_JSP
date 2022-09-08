package com.study.quote.service;

import java.util.List;
import java.util.Map;

import com.study.exception.BizNotFoundException;
import com.study.quote.vo.QuoteSearchVO;
import com.study.quote.vo.QuoteVO;

public interface IQuoteService {
	public List<QuoteVO> getQuoteList(QuoteSearchVO searchVO);
	public List<QuoteVO> getQuoteFavoList(QuoteSearchVO searchVO, String id);
	public QuoteVO getQuote(int ranVal)  throws BizNotFoundException;
}
