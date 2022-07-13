package com.study.reply.service;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotFoundException;
import com.study.reply.dao.IReplyDao;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService{
	
	@Inject
	IReplyDao replyDao;
	
	public List<ReplyVO> getReplyListByParent(ReplySearchVO searchVO) {
		int totalRowCount= replyDao.getReplyCountByParent(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		return replyDao.getReplyListByParent(searchVO);
	}
	@Override
	public void modifyReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {
		// TODO Auto-generated method stub
	}
	@Override
	public void removeReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {
		// TODO Auto-generated method stub
	}
	@Override
	public void registReply(ReplyVO reply) {
		// TODO Auto-generated method stub
	}
}