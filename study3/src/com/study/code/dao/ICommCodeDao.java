package com.study.code.dao;

import java.sql.Connection;
import java.util.List;

import com.study.code.vo.CodeVO;
import com.study.exception.DaoException;

public interface ICommCodeDao {
	public List<CodeVO> getCodeListByParent(String parentCode);
	
}
