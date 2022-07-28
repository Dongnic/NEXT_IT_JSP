package com.test.free.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.free.vo.FreeBoardVO;

@Mapper
public interface IFreeBoardDao {
	public List<FreeBoardVO> getBoardList();
}
