package com.study.quote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.exception.BizNotFoundException;
import com.study.quote.dao.IQuoteDao;
import com.study.quote.vo.QuoteSearchVO;
import com.study.quote.vo.QuoteVO;

@Service
public class QuoteServiceImpl implements IQuoteService{
	
	@Inject
	IQuoteDao quoteDao;
	
	@Override
	public List<QuoteVO> getQuoteList(QuoteSearchVO searchVO) {
		int totalRowCount = quoteDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return quoteDao.getQuoteList(searchVO);
	}

	@Override
	public QuoteVO getQuote(int ranVal) throws BizNotFoundException {

		QuoteVO vo = quoteDao.getQuote(ranVal);
		if (vo == null) {
			throw new BizNotFoundException();
		}
		return vo;
	}

	@Override
	public List<QuoteVO> getQuoteFavoList(QuoteSearchVO searchVO, String id) {
		int totalRowCount = quoteDao.getFavoTotalRowCount(id);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("vo", searchVO);
		return quoteDao.getQuoteFavoList(map);
	}

}
