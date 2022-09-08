package com.study.login.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.study.common.util.CookieUtils;
import com.study.login.service.ILoginService;
import com.study.login.vo.UserVO;

@Controller
public class LoginController {

	// String referer=""; // 빈에서는 절대 데이터 저장 필드 사용 안함,  세션사용
	
	@Inject
	ILoginService loginService;
	 
	@PostMapping("/login/login.wow")
	public String Login(Model model, HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
		String id = req.getParameter("userId");
		String pw = req.getParameter("userPass");
		String save_id = req.getParameter("rememberMe");
		if (save_id == null) {
			CookieUtils cookieUtils = new CookieUtils(req);
			if (cookieUtils.exists("SAVE_ID")) {
				Cookie cookie = CookieUtils.createCookie("SAVE_ID", id, "/", 0);
				resp.addCookie(cookie);
			}
			save_id = "";
		}
		if ((id == null || id.isEmpty()) || (pw == null || pw.isEmpty())) {
			return "redirect:" + "/login/login.wow?msg=" + URLEncoder.encode("입력안했어요", "utf-8");
		} else {
			UserVO user = loginService.getUser(id);
			// loginService

			if (user == null) {
				return "redirect:" + "/login/login.wow?msg="
						+ URLEncoder.encode("아이디 또는 비번확인", "utf-8");
			} else { // id맞았을때
				if (user.getUserPass().equals(pw)) {// 다 맞는경우
					if (save_id.equals("Y")) {
						resp.addCookie(CookieUtils.createCookie("SAVE_ID", id, "/", 3600 * 24 * 7));
					}
					session.setAttribute("USER_INFO", user);
					String referer = (String)session.getAttribute("PREPAGE");
					if(referer.contains("/join")) {
						referer = "/";
					}
					return "redirect:" + referer;
					// resp.sendRedirect("login.jsp");
					// return "redirect:" + "/index.jsp";
				} else {// 비번만 틀린경우
					return "redirect:" + "/login/login.wow?msg="
							+ URLEncoder.encode("아이디 또는 비번확인", "utf-8");
				}
			}
		}
	}

	// get 방식
	@GetMapping("/login/login.wow")
	public String Login(Model model, HttpServletRequest req, HttpSession session) throws IOException {
		session.setAttribute("PREPAGE", req.getHeader("referer"));
		String msg = req.getParameter("msg");
		String id = "";
		String checked = "";
		CookieUtils cookieUtils = new CookieUtils(req);
		if (cookieUtils.exists("SAVE_ID")) {
			id = cookieUtils.getValue("SAVE_ID");
			checked = "checked='checked'";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("id", id);
		model.addAttribute("checked", checked);
		return "login/login";
	}
	
	@RequestMapping("/login/logout.wow")
	public String logout(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws Exception {
		session.removeAttribute("USER_INFO");
		String referer = (String)session.getAttribute("PREPAGE");
		if(referer!=null) {
			return "redirect:" + referer;
		}
		return "redirect:"+"home";
	}

}
