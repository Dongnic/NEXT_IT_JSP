package com.study.home;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.study.exception.BizNotFoundException;
import com.study.favo.dao.IFavoriteDao;
import com.study.favo.vo.FavoriteVO;
import com.study.login.vo.UserVO;
import com.study.quote.dao.IQuoteDao;
import com.study.quote.service.IQuoteService;
import com.study.quote.vo.QuoteSearchVO;
import com.study.quote.vo.QuoteVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	IQuoteService quoteService;
	@Inject
	IFavoriteDao favoriteDao;
	@Inject
	IQuoteDao quoteDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws BizNotFoundException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws BizNotFoundException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@ResponseBody
	@RequestMapping(value = "/getcount.wow")
	public int getcount() {
		int count = quoteDao.getTotalCount();
		return count;
	}
	
	@RequestMapping(value = "/home.wow", method = RequestMethod.GET)
	public String homeload(Locale locale, Model model, HttpSession session, QuoteVO quoteVO) throws BizNotFoundException {
		int ranVal = quoteVO.getQuoNo();
		UserVO vo = (UserVO)session.getAttribute("USER_INFO");
		if(vo != null) {
			List<FavoriteVO> favoList = favoriteDao.getFavoriteList(vo.getUserId());
			model.addAttribute("favoList", favoList);
		}
		
		QuoteVO quote = quoteService.getQuote(ranVal);
		model.addAttribute("quote", quote);
		
		return "home";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/simple/rb.wow", produces = "text/html;charset=utf-8")
	public String rb(HttpServletResponse resp) {
		//resp.setHeader("Content-type", "text/html;charset=utf-8");
		//resp.setContentType("text/html;charset=utf-8");
		return "<html><body><h1>hello</h1></body></html>";
	}
	
	
	
	
	
	
	
	
	
	
	
}
