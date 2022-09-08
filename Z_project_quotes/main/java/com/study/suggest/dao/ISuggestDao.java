package com.study.suggest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.suggest.vo.SuggestSearchVO;
import com.study.suggest.vo.SuggestVO;

@Mapper
public interface ISuggestDao {
	public List<SuggestVO> getSugList(SuggestSearchVO searchVO);
	public List<SuggestVO> getMoreSugList(SuggestSearchVO searchVO);
}
