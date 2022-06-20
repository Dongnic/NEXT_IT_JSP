package com.study.free.dao;

import java.sql.Connection;
import java.util.List;


import com.study.free.vo.FreeBoardVO;

public interface IFreeBoardDao {
	
	
public List<FreeBoardVO> getBoardList() ;
	
  public FreeBoardVO getBoard( int boNo);  
  public int increaseHit(int boNo); 
  public int updateBoard( FreeBoardVO freeBoard);
  public int deleteBoard( FreeBoardVO freeBoard);
  public int insertBoard(FreeBoardVO freeBoard);

  
}
