package com.study.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.exception.DaoException;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao{

	@Override
	public List<MemberVO> getMemberList() {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
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
			sb.append(" ORDER BY REGEXP_REPLACE(mem_id, '[0-9]') , to_number(REGEXP_REPLACE(mem_id, '[^0-9]')) ");
			pstmt= conn.prepareStatement(sb.toString());
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
