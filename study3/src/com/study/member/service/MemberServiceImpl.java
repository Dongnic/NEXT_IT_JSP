package com.study.member.service;

import java.util.List;

import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	IMemberDao memberDao= new MemberDaoOracle();

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {
		
		int totalRowCount= memberDao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return memberDao.getMemberList(searchVO);
	}

	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		MemberVO vo= memberDao.getMember(memId);
		if(vo==null) {
			throw new BizNotFoundException();
		}
		return vo;
	}

	@Override
	public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		MemberVO vo= memberDao.getMember(member.getMemId());
		if(vo==null) {
			throw new BizNotFoundException();
		}
			int cnt = memberDao.updateMember(member);
			if(cnt<1) throw new BizNotEffectedException();
		
	}

	@Override
	public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException {
		MemberVO vo= memberDao.getMember(member.getMemId());
		if(vo==null) {
			throw new BizNotFoundException();
		}
			int cnt = memberDao.deleteMember(member);
			if(cnt<1) throw new BizNotEffectedException();
		
	}

	@Override
	public void registMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException {
		MemberVO vo= memberDao.getMember(member.getMemId());
		if(vo != null && vo.getMemId().equals(member.getMemId())){
			throw new BizDuplicateKeyException();
		}
		if(vo == null || !vo.getMemId().equals(member.getMemId())){
			int cnt=memberDao.insertMember(member);
			if(cnt<1) throw new BizNotEffectedException();
		}
		
	}
}
