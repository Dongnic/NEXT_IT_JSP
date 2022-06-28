package com.study.free.dao;

import java.util.List;

import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public interface IFreeBoardDao {
	
  public int getTotalRowCount(FreeBoardSearchVO FreeBoardSearchVO);
  public List<FreeBoardVO> getBoardList(FreeBoardSearchVO FreeBoardSearchVO) ;

  public FreeBoardVO getBoard( int boNo);  
  public int increaseHit(int boNo); 
  public int updateBoard( FreeBoardVO freeBoard);
  public int deleteBoard( FreeBoardVO freeBoard);
  public int insertBoard(FreeBoardVO freeBoard);

  
}
