package com.study.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {
	
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
