package com.study.favo.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.favo.dao.IFavoriteDao;


@RestController
public class FavoriteController {

	@Inject
	IFavoriteDao favoriteDao;
	
	@RequestMapping(value = "quote/favo.wow")
	public String editFavoriteList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		map.put("no", request.getParameter("no"));
		map.put("id", request.getParameter("id"));
		int check = favoriteDao.rec_check(map);

		if(check > 0) {
			favoriteDao.rec_delete(map);
			return "F";
		}else {
			favoriteDao.rec_update(map);
			return "T";
		}
		
	}
	
	@RequestMapping(value = "/quote/getfavo.wow")
	public String startFavoriteList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		map.put("no", request.getParameter("no"));
		map.put("id", request.getParameter("id"));
		int check = favoriteDao.rec_check(map);
		System.out.println(check);
		if(check > 0) {
			return "F";
		}else {
			return "T";
		}
		
	}
	@RequestMapping(value = "quote/removefavo.wow")
	public void removeFavoriteList(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		map.put("no", request.getParameter("no"));
		map.put("id", request.getParameter("id"));
		int check = favoriteDao.rec_check(map);

		if(check > 0) {
			favoriteDao.rec_delete(map);
		}
	}
}
