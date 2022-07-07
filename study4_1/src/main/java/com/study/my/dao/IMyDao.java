package com.study.my.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMyDao {
	public int getDual();
}
