package com.study.attach.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.attach.vo.AttachVO;

@Mapper
public interface IAttachDao {
	/** 첨부파일 등록 */
	public int insertAttach(AttachVO attach);

	/** 부모번호에 따른 목록 조회 */
	public List<AttachVO> getAttachListByParent(@Param(value = "atchParentNo")int atchParentNo, @Param(value = "atchCategory")String atchCategory);
	
	/** 첨부파일 삭제(여러개) */
	public int deleteAttaches(int[] atchNos);

	/** 첨부파일 상세 조회 */
	public AttachVO getAttach(int atchNo);
	
	/** 다운로드 횟수 증가 */
	public int increaseDownHit(int atchNo);
}