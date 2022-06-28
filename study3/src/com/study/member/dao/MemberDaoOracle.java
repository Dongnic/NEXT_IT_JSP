package com.study.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.exception.DaoException;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao{

	@Override
	public int getTotalRowCount(MemberSearchVO searchVO) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		System.out.println("searchVO: " + searchVO);
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" SELECT	count(*) FROM member								");
			sb.append(" WHERE mem_del_yn = 'N' 										");
			if(StringUtils.isNoneBlank(searchVO.getSearchWord())) {
				switch (searchVO.getSearchType()) {
				case "NM" :
					sb.append(" AND mem_name Like '%' || ? || '%'");
					break;
				case "ID" :
					sb.append(" AND mem_id Like '%' || ? || '%'");
					break;
				case "HP" :
					sb.append(" AND mem_hp Like '%' || ? || '%'");
					break;
				}
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchJob())) {
				sb.append(" AND mem_job = ? ");
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchHobby())) {
				sb.append(" AND mem_hobby = ? ");
			}
			
			pstmt= conn.prepareStatement(sb.toString());
			int idx = 1;
			if(StringUtils.isNoneBlank(searchVO.getSearchWord())) {
				pstmt.setString(idx++, searchVO.getSearchWord());
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchJob())) {
				pstmt.setString(idx++, searchVO.getSearchJob());
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchHobby())) {
				pstmt.setString(idx++, searchVO.getSearchHobby());
			}
			rs= pstmt.executeQuery();
			int count = 0;
			if(rs.next()){
				count= rs.getInt("count(*)");
//				count= rs.getInt(1);
			}
			return count;
		}catch(SQLException e){
			throw new DaoException("getTotalRowCount"+e.getMessage());
		}finally{
			//종료
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
	}
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" SELECT * 														");
			sb.append(" FROM (															");
			sb.append(" SELECT a.*, ROWNUM as rnum   									");
			sb.append(" FROM (															");
			sb.append(" SELECT															");
			sb.append("       mem_id      , mem_pass        , mem_name					");
			sb.append("     , TO_CHAR(mem_bir, 'YYYY.MM.dd') as mem_bir      			");
			sb.append("     , mem_zip       , mem_add1      , mem_add2					");
			sb.append("     , mem_hp        , mem_mail      , mem_job					");
			sb.append("     , mem_hobby  	, mem_mileage   , mem_del_yn				");
			sb.append("     , b.comm_nm AS mem_job_nm, c.comm_nm AS mem_hobby_nm		");
			sb.append(" FROM															");
			sb.append("     member a, comm_code b, comm_code c 							");
			sb.append(" WHERE a.mem_job = b.comm_cd										");
			sb.append(" AND a.mem_hobby = c.comm_cd 									");
			sb.append(" AND mem_del_yn = 'N'  											");
			if(StringUtils.isNoneBlank(searchVO.getSearchWord())) {
				switch (searchVO.getSearchType()) {
				case "NM" :
					sb.append(" AND mem_name Like '%' || ? || '%'");
					break;
				case "ID" :
					sb.append(" AND mem_id Like '%' || ? || '%'");
					break;
				case "HP" :
					sb.append(" AND mem_hp Like '%' || ? || '%'");
					break;
				}
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchJob())) {
				sb.append(" AND mem_job = ? ");
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchHobby())) {
				sb.append(" AND mem_hobby = ? ");
			}
			
			
			sb.append(" ORDER BY REGEXP_REPLACE(mem_id, '[0-9]') , to_number(REGEXP_REPLACE(mem_id, '[^0-9]'))  ");
			sb.append("	)	a																					");
			sb.append("	)	b																					");
			sb.append(" WHERE rnum between ? and ?																");
			pstmt= conn.prepareStatement(sb.toString());
			int idx = 1;
			if(StringUtils.isNoneBlank(searchVO.getSearchWord())) {
				pstmt.setString(idx++, searchVO.getSearchWord());
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchJob())) {
				pstmt.setString(idx++, searchVO.getSearchJob());
			}
			if(StringUtils.isNoneBlank(searchVO.getSearchHobby())) {
				pstmt.setString(idx++, searchVO.getSearchHobby());
			}
			pstmt.setInt(idx++, searchVO.getFirstRow());
			pstmt.setInt(idx++, searchVO.getLastRow());
			
			rs= pstmt.executeQuery(); 
			List<MemberVO> memberList= new ArrayList<>();
			while(rs.next()){
				MemberVO memberL= new MemberVO();
				memberL.setMemId(rs.getString("mem_id"));
				memberL.setMemPass(rs.getString("mem_pass"));
				memberL.setMemName(rs.getString("mem_name"));
				memberL.setMemBir(rs.getString("mem_bir"));
				memberL.setMemZip(rs.getString("mem_zip"));
				memberL.setMemAdd1(rs.getString("mem_add1"));
				memberL.setMemAdd2(rs.getString("mem_add2"));
				memberL.setMemHp(rs.getString("mem_hp"));
				memberL.setMemMail(rs.getString("mem_mail"));
				memberL.setMemJob(rs.getString("mem_job"));
				memberL.setMemJobNm(rs.getString("mem_job_nm"));
				memberL.setMemHobby(rs.getString("mem_hobby"));
				memberL.setMemHobbyNm(rs.getString("mem_hobby_nm"));
				memberL.setMemMileage(rs.getInt("mem_mileage"));
				memberL.setMemDelYn(rs.getString("mem_del_yn"));
				
				memberList.add(memberL);
			}
			return memberList;
		}catch(SQLException e){
			throw new DaoException("memberList"+e.getMessage());
		}finally{
			//종료
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
	}

	@Override
	public MemberVO getMember(String memId) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{		
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" SELECT															");
			sb.append("       mem_id      , mem_pass        , mem_name					");
			sb.append("     , TO_CHAR(mem_bir, 'YYYY-MM-dd') as mem_bir      			");
			sb.append("     , mem_zip       , mem_add1      , mem_add2					");
			sb.append("     , mem_hp        , mem_mail      , mem_job					");
			sb.append("     , mem_hobby  	, mem_mileage   , mem_del_yn				");
			sb.append("     , b.comm_nm AS mem_job_nm, c.comm_nm AS mem_hobby_nm		");
			sb.append(" FROM															");
			sb.append("     member a, comm_code b, comm_code c 							");
			sb.append(" WHERE a.mem_job = b.comm_cd										");
			sb.append(" AND a.mem_hobby = c.comm_cd 									");
			sb.append(" AND   mem_id= ?	      											");
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, memId);
			rs = pstmt.executeQuery(); 
			if(rs.next()){
				MemberVO memberL= new MemberVO();
				memberL.setMemId(rs.getString("mem_id"));
				memberL.setMemPass(rs.getString("mem_pass"));
				memberL.setMemName(rs.getString("mem_name"));
				memberL.setMemBir(rs.getString("mem_bir"));
				memberL.setMemZip(rs.getString("mem_zip"));
				memberL.setMemAdd1(rs.getString("mem_add1"));
				memberL.setMemAdd2(rs.getString("mem_add2"));
				memberL.setMemHp(rs.getString("mem_hp"));
				memberL.setMemMail(rs.getString("mem_mail"));
				memberL.setMemJob(rs.getString("mem_job"));
				memberL.setMemJobNm(rs.getString("mem_job_nm"));
				memberL.setMemHobby(rs.getString("mem_hobby"));
				memberL.setMemHobbyNm(rs.getString("mem_hobby_nm"));
				memberL.setMemMileage(rs.getInt("mem_mileage"));
				memberL.setMemDelYn(rs.getString("mem_del_yn"));
				
				return memberL;
			}
		}catch(SQLException e){
			throw new DaoException("getMember"+e.getMessage());
		}finally{
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null)  { try{pstmt.close();   }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
		return null;
	}

	@Override
	public int updateMember(MemberVO memberL) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append(" UPDATE member 					");
			sb.append(" SET 							");
			sb.append("		 mem_pass = ?				");
			sb.append("		,mem_name = ?				");
			sb.append("		,mem_zip = ?				");
			sb.append("		,mem_add1 = ?				");
			sb.append("		,mem_add2 = ?				");
			sb.append("		,mem_bir = ?				");
			sb.append("		,mem_mail = ?				");
			sb.append("		,mem_hp = ?					");
			sb.append("		,mem_job = ?				");
			sb.append("		,mem_hobby = ?				");
			sb.append(" WHERE 1=1						");
			sb.append(" AND mem_id = ?					");
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, memberL.getMemPass());
			pstmt.setString(idx++, memberL.getMemName());
			pstmt.setString(idx++, memberL.getMemZip());
			pstmt.setString(idx++, memberL.getMemAdd1());
			pstmt.setString(idx++, memberL.getMemAdd2());
			pstmt.setString(idx++, memberL.getMemBir());
			pstmt.setString(idx++, memberL.getMemMail());
			pstmt.setString(idx++, memberL.getMemHp());
			pstmt.setString(idx++, memberL.getMemJob());
			pstmt.setString(idx++, memberL.getMemHobby());
			pstmt.setString(idx++, memberL.getMemId());
			
			int cnt = pstmt.executeUpdate(); 
			return cnt;
		}catch (SQLException e){
			throw new DaoException("updateMember : "+ e.getMessage());
		}finally{
			if(rs !=null) {try{ rs.close();}catch(Exception e){}}
			if(pstmt !=null) {try{ pstmt.close();}catch(Exception e){}}
			if(conn !=null) {try{ conn.close();}catch(Exception e){}}
		}
	}

	@Override
	public int deleteMember(MemberVO memberL) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append(" UPDATE member 					");
			sb.append(" SET 							");
			sb.append("		 mem_del_yn = 'Y'			");
			sb.append(" WHERE 1=1						");
			sb.append(" AND mem_id = ?					");
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			
			pstmt.setString(idx++, memberL.getMemId());
			
			int cnt = pstmt.executeUpdate(); 
			return cnt;
		}catch (SQLException e){
			throw new DaoException("deleteMember : "+ e.getMessage());
		}finally{
			if(rs !=null) {try{ rs.close();}catch(Exception e){}}
			if(pstmt !=null) {try{ pstmt.close();}catch(Exception e){}}
			if(conn !=null) {try{ conn.close();}catch(Exception e){}}
		}
	}

	@Override
	public int insertMember(MemberVO memberL) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append(" INSERT INTO member (									");
			sb.append("       mem_id          , mem_pass       , mem_name		");
			sb.append("     , mem_zip    	  , mem_add1       , mem_add2		");
			sb.append("     , mem_bir         , mem_mail 	   , mem_hp			");
			sb.append("     , mem_job		  , mem_hobby	   , mem_mileage	");
			sb.append("     , mem_del_yn	 									");
			sb.append(" ) VALUES (												");
			sb.append("       ?		    , ?  	     , ?						");
			sb.append("     , ?         , ?          , ?						");
			sb.append("     , ?         , ?          , ?						");
			sb.append("     , ?         , ? 	     , ?						");
			sb.append("     , 'N'												");
			sb.append(" )														");
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, memberL.getMemId());
			pstmt.setString(idx++, memberL.getMemPass());
			pstmt.setString(idx++, memberL.getMemName());
			pstmt.setString(idx++, memberL.getMemZip());
			pstmt.setString(idx++, memberL.getMemAdd1());
			pstmt.setString(idx++, memberL.getMemAdd2());
			pstmt.setString(idx++, memberL.getMemBir());
			pstmt.setString(idx++, memberL.getMemMail());
			pstmt.setString(idx++, memberL.getMemHp());
			pstmt.setString(idx++, memberL.getMemJob());
			pstmt.setString(idx++, memberL.getMemHobby());
			pstmt.setInt(idx++, 0);
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		}catch (SQLException e){
			throw new DaoException("insertMember : "+ e.getMessage());
		}finally{
			if(rs !=null) {try{ rs.close();}catch(Exception e){}}
			if(pstmt !=null) {try{ pstmt.close();}catch(Exception e){}}
			if(conn !=null) {try{ conn.close();}catch(Exception e){}}
		}
	}

}
