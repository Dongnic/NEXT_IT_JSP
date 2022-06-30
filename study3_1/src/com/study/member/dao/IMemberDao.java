package com.study.member.dao;

import java.sql.Connection;
import java.util.List;

import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public interface IMemberDao {

	public int getTotalRowCount(MemberSearchVO searchVO);
	public List<MemberVO> getMemberList(MemberSearchVO searchVO);
	public MemberVO getMember(String memId);
	public int updateMember(MemberVO member);
	public int deleteMember(MemberVO member);
	public int insertMember(MemberVO member);
	
}
