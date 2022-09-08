package com.study.favo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.favo.vo.FavoriteVO;

@Mapper
public interface IFavoriteDao {
	public List<FavoriteVO> getFavoriteList(String id);
	public int rec_check(Map<String, Object> map);
	public int rec_update(Map<String, Object> map);
	public int rec_delete(Map<String, Object> map);
}
