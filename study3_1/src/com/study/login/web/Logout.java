package com.study.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.servlet.Handler;

public class Logout implements Handler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session=req.getSession();
		session.removeAttribute("USER_INFO");
		return "redirect:"+req.getContextPath()+"/index.jsp";
	}
}
