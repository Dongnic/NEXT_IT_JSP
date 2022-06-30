package com.study.code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.code.dao.ICommCodeDao;
import com.study.code.vo.CodeVO;
import com.study.common.util.MybatisSqlSessionFactory;


public class CommCodeServiceImpl implements ICommCodeService {
	
	SqlSessionFactory sqlSessionFactory= MybatisSqlSessionFactory.getSqlSessionFactory();
	@Override
	public List<CodeVO> getCodeListByParent(String parentCode) {
															// true는 자동커밋 false는 수동
		try(SqlSession session= sqlSessionFactory.openSession(true)) {
			ICommCodeDao codeDao=session.getMapper(ICommCodeDao.class);
			return codeDao.getCodeListByParent(parentCode);
		}
		
	}
	
}
