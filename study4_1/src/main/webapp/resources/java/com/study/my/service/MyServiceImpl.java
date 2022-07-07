package com.study.my.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.my.dao.IMyDao;

@Service
public class MyServiceImpl implements IMyService{
	
	@Inject
	IMyDao myDao;
	
	@Override
	public int getDual() {
		return myDao.getDual();
	}
}
