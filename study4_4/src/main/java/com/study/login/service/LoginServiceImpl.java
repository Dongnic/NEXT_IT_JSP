package com.study.login.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;

@Service
public class LoginServiceImpl implements ILoginService{
	
	@Inject
	IMemberDao memberDao;
	
	@Override
	public UserVO getUser(String userId) {
		MemberVO member =memberDao.getMember(userId);
		if(member==null) {
			return null;
		}
		UserVO user=new UserVO();
		user.setUserId(member.getMemId() );
		user.setUserPass(member.getMemPass() );
		user.setUserName(member.getMemName() );
		if(user.getUserId().equals("a004")) {
			user.setUserRole("MANAGER");  //임시
		}else {
			user.setUserRole("MEMBER");  //임시
		}
		return user;
	}

}
