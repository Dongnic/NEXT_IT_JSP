package com.study.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
	
	private Map<String,Cookie> cookieMap=new HashMap<String, Cookie>();
	
	public CookieUtils(HttpServletRequest request) { //생성될 때 그 request의 모든 쿠키가 cookieMap에 담김
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}
	
	public boolean exists(String name) { 
		return cookieMap.get(name)!=null; 
	}
	
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}  //null을 return할 수도 있는데 이때 검사를 직접하는것보단 exists를 이용해서...
	
	public String getValue(String name) throws IOException{
		Cookie cookie=cookieMap.get(name);
		if(cookie==null) return null;
		return URLDecoder.decode(cookie.getValue(),"utf-8");
	}
	
	public static Cookie createCookie(String name, String value) throws IOException {
		return createCookie(name, value, "", "", -1);
	}
	
	public static Cookie createCookie(String name,String value,String path) throws IOException{
		return createCookie(name,value,"",path,-1);
	}
	
	
	public static Cookie createCookie(String name,String value,int maxAge) throws IOException{
		return createCookie(name,value,"","",maxAge);
	}
	
	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		return createCookie(name, value, "", path, maxAge);
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
}
