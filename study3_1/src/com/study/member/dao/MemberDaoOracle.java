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
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class MemberDaoOracle  implements IMemberDao{

	@Override
	public int getTotalRowCount(MemberSearchVO searchVO) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			//조건에 맞는 글 개수를 얻어야됩니다.
			// searchVO에는 searchType은 무조건있어요. 
			// searchWord,category는 사용자가 입력해야 있어요
			sb.append("SELECT count(*)    ");
			sb.append("FROM member    ");
			sb.append("WHERE 1=1          ");
			
			//searchVO.getSearchWord() !=null &&  searchVO.getSearchWord().equals("")
			if(!StringUtils.isBlank(searchVO.getSearchWord())) {
				//입력함
					switch (searchVO.getSearchType()) {
					case "ID":
						sb.append(" AND mem_id LIKE '%'||?||'%' ");
						break;
					case "NM":
						sb.append(" AND mem_name LIKE '%'||?||'%' ");	
						break;
					case "HP":
						sb.append(" AND mem_hp LIKE '%'||?||'%' ");	
						break;
				}
			}
				// 검색어만 가지고 검색할 때,  분류만 가지고 검색할때,  검색어,분류 둘다 검색할 때 
			if(!StringUtils.isBlank(searchVO.getSearchJob())) {
				sb.append(" AND mem_job =?         ");
			}
			if(!StringUtils.isBlank(searchVO.getSearchHobby())) {
				sb.append(" AND mem_hobby =?         ");
			}
			
			pstmt=conn.prepareStatement(sb.toString());
			int i=1;
			if(!StringUtils.isBlank(searchVO.getSearchWord())) {
				pstmt.setString(i++, searchVO.getSearchWord());
			}
			if(!StringUtils.isBlank(searchVO.getSearchJob())) {
				pstmt.setString(i++, searchVO.getSearchJob());
			}
			if(!StringUtils.isBlank(searchVO.getSearchHobby())) {
				pstmt.setString(i++, searchVO.getSearchHobby());
			}
			
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt= rs.getInt(1);
				return cnt;
			}
			return 0;
		}catch (SQLException e) {
			throw new DaoException("totalRowCount  "+e.getMessage(),e);
		}finally {
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();} catch(Exception e){} }
		}
	}
	
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		Connection conn=null;
		   PreparedStatement pstmt=null;  //Statement랑 큰차이는 없는데...
			ResultSet rs=null;
		   
		   try{
			   conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			   //커넥션풀 study에서 connection 요구
			   
			   StringBuffer sb=new StringBuffer();
				sb.append("  SELECT *  FROM                  (                           ");
				sb.append(" SELECT a.* , rownum as rnum FROM (                           ");
				sb.append("SELECT                                                        ");
				sb.append("       to_char(mem_bir,'YYYY-MM-DD') AS mem_bir              ,");
				sb.append("       mem_id     ,     mem_pass     ,       mem_name        ,");
				sb.append("                        mem_zip      ,       mem_add1        ,");
				sb.append("       mem_add2   ,     mem_hp       ,       mem_mail        ,");
				sb.append("       mem_job    ,     mem_hobby    ,       mem_mileage     ,");
				sb.append("       mem_del_yn                                            ,");
				sb.append("       b.comm_nm AS mem_job_nm                               ,");
				sb.append("       c.comm_nm AS mem_hobby_nm                              ");
				sb.append("FROM member   a, comm_code b, comm_code c                     ");
				sb.append(" WHERE mem_job=b.comm_cd                                      ");
				sb.append(" AND   mem_hobby=c.comm_cd                                    ");
				if(!StringUtils.isBlank(searchVO.getSearchWord())) {
					//입력함
						switch (searchVO.getSearchType()) {
						case "ID":
							sb.append(" AND mem_id LIKE '%'||?||'%' ");
							break;
						case "NM":
							sb.append(" AND mem_name LIKE '%'||?||'%' ");	
							break;
						case "HP":
							sb.append(" AND mem_hp LIKE '%'||?||'%' ");	
							break;
					}
				}
					// 검색어만 가지고 검색할 때,  분류만 가지고 검색할때,  검색어,분류 둘다 검색할 때 
				if(!StringUtils.isBlank(searchVO.getSearchJob())) {
					sb.append(" AND mem_job =?         ");
				}
				if(!StringUtils.isBlank(searchVO.getSearchHobby())) {
					sb.append(" AND mem_hobby =?         ");
				}
				
				sb.append("ORDER by mem_id desc                                            ");
				sb.append("     ) a     )b                                                "); 
				sb.append(" WHERE rnum between  ? and ?                                   ");
				
		 		
			   pstmt=conn.prepareStatement(sb.toString()); //쿼리문 
			   
			   int i=1;
				if(!StringUtils.isBlank(searchVO.getSearchWord())) {
					pstmt.setString(i++, searchVO.getSearchWord());
				}
				if(!StringUtils.isBlank(searchVO.getSearchJob())) {
					pstmt.setString(i++, searchVO.getSearchJob());
				}
				if(!StringUtils.isBlank(searchVO.getSearchHobby())) {
					pstmt.setString(i++, searchVO.getSearchHobby());
				}
			   
				pstmt.setInt(i++, searchVO.getFirstRow());
				pstmt.setInt(i++, searchVO.getLastRow());
			   rs=pstmt.executeQuery();  //실행
			   
			   List<MemberVO> memberList=new ArrayList<MemberVO>();
			   while(rs.next()){
				   MemberVO member=new MemberVO();
				   member.setMemId(rs.getString("mem_id")); member.setMemPass(rs.getString("mem_pass"));
				   member.setMemName(rs.getString("mem_name")); member.setMemBir(rs.getString("mem_bir"));
				   member.setMemZip(rs.getString("mem_zip")); member.setMemAdd1(rs.getString("mem_add1"));
				   member.setMemAdd2(rs.getString("mem_add2")); member.setMemHp(rs.getString("mem_hp"));
				   member.setMemMail(rs.getString("mem_mail")); member.setMemJob(rs.getString("mem_job"));
				   member.setMemHobby(rs.getString("mem_hobby")); member.setMemMileage(rs.getInt("mem_mileage"));
				   member.setMemDelYn(rs.getString("mem_del_yn"));
				   member.setMemJobNm(rs.getString("mem_job_nm"));
				   member.setMemHobbyNm(rs.getString("mem_hobby_nm"));
				   memberList.add(member);
			   }
			   return memberList;
		   }catch(SQLException e){
			   throw new DaoException("getMemberList"+e.getMessage(),e);
		   }finally{
			   if(rs!=null){try{rs.close();} catch(Exception e){} }
				if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
				if(conn!=null){try{conn.close();} catch(Exception e){} }
		   }
	}

	@Override
	public MemberVO getMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT                                                        ");
			sb.append("       to_char(mem_bir,'YYYY-MM-DD') AS mem_bir               ,");
			sb.append("       mem_id     ,     mem_pass     ,       mem_name        ,");
			sb.append("                        mem_zip      ,       mem_add1        ,");
			sb.append("       mem_add2   ,     mem_hp       ,       mem_mail        ,");
			sb.append("       mem_job    ,     mem_hobby    ,       mem_mileage     ,");
			sb.append("       mem_del_yn                                            ,");
			sb.append("       b.comm_nm AS mem_job_nm                               ,");
			sb.append("       c.comm_nm AS mem_hobby_nm                              ");
			sb.append("FROM member   a, comm_code b, comm_code c                     ");
			sb.append("WHERE mem_id=?                                               ");
			sb.append(" AND mem_job=b.comm_cd                                      ");
			sb.append(" AND   mem_hobby=c.comm_cd                                    ");
		   // sb.append("WHERE mem_id='"+memId+"'"        ")  stmt의''처리가 불편해서 나온게 pstmt
		    pstmt=conn.prepareStatement(sb.toString());
		    
		    //쿼리 실행전 ? 처리
		    pstmt.setString(1,memId );   //물음표 순서대로 
		    
		    rs=pstmt.executeQuery();
		    
		    
		    if(rs.next()){
		    	  MemberVO member=new MemberVO();
				  member.setMemId(rs.getString("mem_id")); member.setMemPass(rs.getString("mem_pass"));
				  member.setMemName(rs.getString("mem_name")); member.setMemBir(rs.getString("mem_bir"));
				  member.setMemZip(rs.getString("mem_zip")); member.setMemAdd1(rs.getString("mem_add1"));
				  member.setMemAdd2(rs.getString("mem_add2")); member.setMemHp(rs.getString("mem_hp"));
				  member.setMemMail(rs.getString("mem_mail")); member.setMemJob(rs.getString("mem_job"));
				  member.setMemHobby(rs.getString("mem_hobby")); member.setMemMileage(rs.getInt("mem_mileage"));
				  member.setMemDelYn(rs.getString("mem_del_yn"));
				  member.setMemJobNm(rs.getString("mem_job_nm"));
				   member.setMemHobbyNm(rs.getString("mem_hobby_nm"));
				  return member;
		    }
			return null;
		} catch (SQLException e) {
			throw new DaoException("getMember"+e.getMessage(),e);
		}finally{
		   if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();} catch(Exception e){} }
	   }
	}

	@Override
	public int updateMember(MemberVO member) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			sb.append("UPDATE member SET              ");
			sb.append("mem_name=?                     ");
			sb.append(",mem_bir=?                     ");
			sb.append(",mem_zip=?                     ");
			sb.append(",mem_add1=?                    ");
			sb.append(",mem_add2=?                    ");
			sb.append(",mem_hp=?                      ");
			sb.append(",mem_mail=?                    ");
			sb.append(",mem_job=?                     ");
			sb.append(",mem_hobby=?                   ");
			sb.append("WHERE mem_id=?                 ");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			int i=1;
			pstmt.setString(i++, member.getMemName());
			pstmt.setString(i++, member.getMemBir());
			pstmt.setString(i++, member.getMemZip());
			pstmt.setString(i++, member.getMemAdd1());
			pstmt.setString(i++, member.getMemAdd2());
			pstmt.setString(i++, member.getMemHp());
			pstmt.setString(i++, member.getMemMail());
			pstmt.setString(i++, member.getMemJob());
			pstmt.setString(i++, member.getMemHobby());
			pstmt.setString(i++, member.getMemId());
			
			int cnt=pstmt.executeUpdate();
		    return cnt;
			
			
		}catch(SQLException e){
			throw new DaoException("updateMember"+e.getMessage(),e);
		}finally{
			   if(rs!=null){try{rs.close();} catch(Exception e){} }
				if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
				if(conn!=null){try{conn.close();} catch(Exception e){} }
		   }
	}

	@Override
	public int deleteMember(MemberVO member) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			sb.append("UPDATE member SET              ");
			sb.append("mem_del_yn='Y'                 ");
			sb.append("WHERE mem_id=?                 ");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, member.getMemId());
			int cnt=pstmt.executeUpdate();
			return cnt;
		}catch(SQLException e){
			throw new DaoException("deleteMember"+e.getMessage(),e);
		}finally{
			   if(rs!=null){try{rs.close();} catch(Exception e){} }
				if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
				if(conn!=null){try{conn.close();} catch(Exception e){} }
		   }


	}

	@Override
	public int insertMember(MemberVO member) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			
			sb.append("INSERT INTO member VALUES(              ");
			sb.append(" ?,     ?,    ?,                        ");//id,pass,name
			sb.append(" ?,     ?,    ?,                        ");//bir,zip,add1
			sb.append(" ?,     ?,    ?,                        ");//add2,hp,mail
			sb.append(" ?,     ?,    0,                        ");//job,hobby,mileage
			sb.append(" 'N'                                    ");//del_yn
			sb.append("  )                                     ");
			pstmt=conn.prepareStatement(sb.toString());
			int i=1;
			pstmt.setString(i++, member.getMemId());pstmt.setString(i++, member.getMemPass());
			pstmt.setString(i++, member.getMemName());pstmt.setString(i++, member.getMemBir());
			pstmt.setString(i++, member.getMemZip());pstmt.setString(i++, member.getMemAdd1());
			pstmt.setString(i++, member.getMemAdd2());pstmt.setString(i++, member.getMemHp());
			pstmt.setString(i++, member.getMemMail());pstmt.setString(i++, member.getMemJob());
			pstmt.setString(i++, member.getMemHobby());
			
			int cnt=pstmt.executeUpdate();
			return cnt;
		}catch (SQLException e){
			throw new DaoException("insertMember"+e.getMessage(),e);
		}finally{
			   if(rs!=null){try{rs.close();} catch(Exception e){} }
				if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
				if(conn!=null){try{conn.close();} catch(Exception e){} }
		   }
	}

}
