package com.study.code.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.code.dao.ICommCodeDao;
import com.study.code.vo.CodeVO;
import com.study.exception.DaoException;

public class CommCodeDaoOracle implements ICommCodeDao {

	@Override
	public List<CodeVO> getCodeListByParent(String parentCode) {
		// DB접근해서 쿼리쓰기 return으로는 List<codeVo> return
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try{
			conn= DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb= new StringBuffer();
			sb.append(" SELECT	*												");
			sb.append(" FROM comm_code    										");
			sb.append(" WHERE 1=1										    	");
			sb.append(" AND comm_parent= ? 		  							 	");
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, parentCode);
			rs = pstmt.executeQuery(); 
			List<CodeVO> codeList= new ArrayList<>();
			while(rs.next()){
				CodeVO code= new CodeVO();
				code.setCommCd(rs.getString("comm_cd"));
				code.setCommNm(rs.getString("comm_nm"));
				code.setCommParent(rs.getString("comm_parent"));
				code.setCommOrd(rs.getInt("comm_ord"));
				
				codeList.add(code);
				
			}
			return codeList;
		}catch(SQLException e){
			throw new DaoException("codeList"+e.getMessage());
		}finally{
			//종료
			if(rs!=null)  { try{rs.close();   }catch(Exception e){}}
			if(pstmt!=null){ try{pstmt.close(); }catch(Exception e){}}
			if(conn!=null){ try{conn.close(); }catch(Exception e){}}
		}
	}

}
