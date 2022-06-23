package com.study.code.service;

import java.util.List;

import com.study.code.dao.ICommCodeDao;
import com.study.code.dao.CommCodeDaoOracle;
import com.study.code.vo.CodeVO;

public class CommCodeServiceImpl implements ICommCodeService{
		ICommCodeDao codeDao= new CommCodeDaoOracle();
		
		@Override
		public List<CodeVO> getCodeListByParent(String parentCode) {
			// TODO Auto-generated method stub
			return codeDao.getCodeListByParent(parentCode);
		}
		
}

