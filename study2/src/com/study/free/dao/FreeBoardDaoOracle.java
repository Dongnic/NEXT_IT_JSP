package com.study.free.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.exception.DaoException;
import com.study.free.vo.FreeBoardVO;

public class FreeBoardDaoOracle implements IFreeBoardDao{

	@Override
	public List<FreeBoardVO> getBoardList() {
		// TODO Auto-generated method stub
	  	Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" SELECT														");
			sb.append("      bo_no         , bo_title       , bo_category 			");
			sb.append("    , bo_writer     , bo_pass        , bo_content		  	");
			sb.append("    , bo_hit      								   			");
			sb.append("    , TO_CHAR(bo_reg_date,'YYYY-MM-dd') AS bo_reg_date		");
			sb.append("    , TO_CHAR(bo_mod_date,'YYYY-MM-dd') AS bo_mod_date		");
			sb.append("    , bo_del_yn												");
			sb.append(" FROM														");
			sb.append("    free_board 												");
			pstmt= conn.prepareStatement(sb.toString());
			
			rs= pstmt.executeQuery();
			List<FreeBoardVO> freeBoardList= new ArrayList<>();
			while(rs.next()){
				FreeBoardVO freeBoard= new FreeBoardVO();
				freeBoard.setBoNo(rs.getInt("bo_no"));
				freeBoard.setBoTitle(rs.getString("bo_title"));
				freeBoard.setBoCategory(rs.getString("bo_category"));
				freeBoard.setBoWriter(rs.getString("bo_writer"));
				freeBoard.setBoPass(rs.getString("bo_pass"));
				freeBoard.setBoContent(rs.getString("bo_content"));
				freeBoard.setBoHit(rs.getInt("bo_hit"));
				freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
				freeBoard.setBoModDate(rs.getString("bo_mod_date"));
				freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
				
				freeBoardList.add(freeBoard);
			}
			 return freeBoardList;
			
		}catch(SQLException e){
			// 1번 아무것도 안함
			// 2번 콘솔에 찍히긴 하는데 코드는 정상 실행??
			//     SQLException이 났는데 정상실행되면 안되는거 아닌가? 원래는 파란화면
			// 3번 예외 전환
			// SQLException을 DaoException 바꿔서 던지자
			
			// return과 같이 throw new도 메소드를 끝낸다.
			throw new DaoException("getBoardList"+e.getMessage());
		}finally{
			//종료
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
	}

	@Override
	public FreeBoardVO getBoard(int boNo) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" SELECT														");
			sb.append("      bo_no         , bo_title       , bo_category 			");
			sb.append("    , bo_writer     , bo_pass        , bo_content		  	");
			sb.append("    , bo_hit      								   			");
			sb.append("    , TO_CHAR(bo_reg_date,'YYYY-MM-dd') AS bo_reg_date		");
			sb.append("    , TO_CHAR(bo_mod_date,'YYYY-MM-dd') AS bo_mod_date		");
			sb.append("    , bo_del_yn												");
			sb.append(" FROM														");
			sb.append("    free_board 												");
			sb.append(" WHERE 1=1	 			");
			sb.append(" AND   bo_no= ?	        ");
			pstmt= conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setInt(idx++, boNo);
			rs= pstmt.executeQuery();
			if(rs.next()){
				FreeBoardVO freeBoard= new FreeBoardVO();
				freeBoard.setBoNo(rs.getInt("bo_no"));
				freeBoard.setBoTitle(rs.getString("bo_title"));
				freeBoard.setBoCategory(rs.getString("bo_category"));
				freeBoard.setBoWriter(rs.getString("bo_writer"));
				freeBoard.setBoPass(rs.getString("bo_pass"));
				freeBoard.setBoContent(rs.getString("bo_content"));
				freeBoard.setBoHit(rs.getInt("bo_hit"));
				freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
				freeBoard.setBoModDate(rs.getString("bo_mod_date"));
				freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
				
				return freeBoard;
			}
			
		}catch(SQLException e){
			throw new DaoException("getBoard"+e.getMessage());
		}finally{
			//종료
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
		return null;
	}

	@Override
	public int increaseHit(int boNo) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" UPDATE free_board 				");
			sb.append(" SET 							");
			sb.append("		 bo_hit = bo_hit+1			");
			sb.append(" WHERE bo_no = ?					");
			
			pstmt= conn.prepareStatement(sb.toString());
			pstmt.setInt(1, boNo);
			int cnt = pstmt.executeUpdate();
			return cnt;
		}catch(SQLException e){
			throw new DaoException("increaseHit"+e.getMessage());
		}finally{
			//종료
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
	}

	@Override
	public int updateBoard(FreeBoardVO freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(FreeBoardVO freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBoard(FreeBoardVO freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

}
