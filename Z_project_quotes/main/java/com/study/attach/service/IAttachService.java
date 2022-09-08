package com.study.attach.service;

import com.study.attach.vo.AttachVO;
import com.study.exception.BizException;

public interface IAttachService {
	/** 첨부파일 상세 조회 */
	public AttachVO getAttach(int atchNo) throws BizException;

	/** 다운로드 횟수 증가 */
	public void increaseDownHit(int atchNo) throws BizException;
}
