package com.study.reply.service;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotEffectedException;
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
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		System.out.println("vo: "+vo.getReMemId());
		System.out.println("reply: "+reply.getReMemId());
		if(!vo.getReMemId().equals(reply.getReMemId())) {
			throw new BizAccessFailException();
		}
		int cnt = replyDao.updateReply(reply);
	}
	@Override
	public void removeReply(ReplyVO reply) throws BizNotFoundException, BizAccessFailException {
		ReplyVO vo = replyDao.getReply(reply.getReNo());
		if(vo==null) {
			throw new BizNotFoundException();
		}
		if(!vo.getReMemId().equals(reply.getReMemId())) {
			throw new BizAccessFailException();
		}
		int cnt = replyDao.deleteReply(reply);
	}
	@Override
	public void changeReply(ReplyVO reply) {
		int cnt = replyDao.changeReply(reply);
	}
	@Override
	public void registReply(ReplyVO reply) {
		int cnt = replyDao.insertReply(reply);
	}
	
	
	
}