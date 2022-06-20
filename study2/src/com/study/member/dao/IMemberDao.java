package com.study.member.dao;

import java.sql.Connection;
import java.util.List;

import com.study.member.vo.MemberVO;

public interface IMemberDao {


	public List<MemberVO> getMemberList();
	public MemberVO getMember(String memId);
	public int updateMember(MemberVO member);
	public int deleteMember(MemberVO member);
	public int insertMember(MemberVO member);
	
}
