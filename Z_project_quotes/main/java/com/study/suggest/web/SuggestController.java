package com.study.suggest.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.suggest.dao.ISuggestDao;
import com.study.suggest.vo.SuggestSearchVO;
import com.study.suggest.vo.SuggestVO;

@RestController
public class SuggestController {

	@Inject
	ISuggestDao suggestDao;
	
	@RequestMapping(value = "/quote/suggest.wow")
	public Map<String,Object> getSuggestList(SuggestSearchVO searchVO){
		List<SuggestVO> sugList = suggestDao.getSugList(searchVO);
		System.out.println("-----첫번째-----");
		System.out.println(sugList);
		if(sugList.size() <= 7) {
			searchVO.setSearchCount(searchVO.getSearchCount()-sugList.size());
			sugList.addAll(suggestDao.getMoreSugList(searchVO));
		}
		System.out.println("-----두번째-----");
		System.out.println(sugList);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", sugList);
		return map;
	}
	
}
