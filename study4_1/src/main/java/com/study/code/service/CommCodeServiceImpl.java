package com.study.code.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.code.dao.ICommCodeDao;
import com.study.code.vo.CodeVO;


// 모든 Service에서
// 1. @Service 붙이기
// 2. 필드에 Dao 선언 후 @Inject 붙이기
// 3. 메소드 내에서 try(){  } 지우기 
// 4. Dao가 필드에 있는 것만 쓰면 돼지. 메소드 내에서 선언하면 안됨

@Service
public class CommCodeServiceImpl implements ICommCodeService {
	
	@Inject
	ICommCodeDao codeDao;
	
	@Override
	public List<CodeVO> getCodeListByParent(String parentCode) {
			return codeDao.getCodeListByParent(parentCode);
	}
	
}
