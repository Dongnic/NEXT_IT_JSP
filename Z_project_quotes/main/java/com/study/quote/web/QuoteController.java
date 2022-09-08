package com.study.quote.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.favo.dao.IFavoriteDao;
import com.study.favo.vo.FavoriteVO;
import com.study.login.vo.UserVO;
import com.study.quote.service.IQuoteService;
import com.study.quote.vo.QuoteSearchVO;
import com.study.quote.vo.QuoteVO;

@Controller
public class QuoteController {

	@Inject
	IQuoteService quoteService;
	
	@Inject
	IFavoriteDao favoriteDao;
	
	@RequestMapping(value = "/quote/quoteList.wow")
	public String quoteList(Model model, @ModelAttribute("searchVO") QuoteSearchVO searchVO, HttpSession session) {
		UserVO vo = (UserVO)session.getAttribute("USER_INFO");
		if(vo != null) {
			List<FavoriteVO> favoList = favoriteDao.getFavoriteList(vo.getUserId());
			model.addAttribute("favoList", favoList);
		}
		List<QuoteVO> quoteList = quoteService.getQuoteList(searchVO);
		model.addAttribute("quoteList", quoteList);
		return "/quote/quoteList";
	}
	@RequestMapping(value = "/quote/quoteFavo.wow")
	public String quoteFavo(Model model, @ModelAttribute("searchVO") QuoteSearchVO searchVO, HttpSession session) {
		UserVO vo = (UserVO)session.getAttribute("USER_INFO");
		if(vo != null) {
			List<FavoriteVO> favoList = favoriteDao.getFavoriteList(vo.getUserId());
			model.addAttribute("favoList", favoList);
			List<QuoteVO> quoteFavoList = quoteService.getQuoteFavoList(searchVO, vo.getUserId());
			model.addAttribute("quoteFavoList", quoteFavoList);
		}
		return "/quote/quoteFavo";
	}
	
}
