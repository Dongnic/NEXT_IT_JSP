package com.study.free.dao;

import java.sql.Connection;
import java.util.List;

import com.study.common.vo.PagingVO;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public interface IFreeBoardDao {

	public int getTotalRowCount(FreeBoardSearchVO searchVO);
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO);
	public FreeBoardVO getBoard(int boNo);

	public int increaseHit(int boNo);

	public int updateBoard(FreeBoardVO freeBoard);

	public int deleteBoard(FreeBoardVO freeBoard);

	public int insertBoard(FreeBoardVO freeBoard);

}
