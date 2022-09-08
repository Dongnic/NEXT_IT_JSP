package com.study.reply.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotFoundException;
import com.study.reply.service.IReplyService;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;
@RestController
public class ReplyController {
		
	@Inject
	IReplyService replyService;
	
	@RequestMapping(value = "/reply/replyList.wow")
	public Map<String,Object> replyList(ReplySearchVO searchVO){
		Map<String,Object> map = new HashMap<String, Object>();
		List<ReplyVO> replyList = replyService.getReplyListByParent(searchVO);
		map.put("success",true);
		map.put("result", replyList);
		return map;
	}
	
	@RequestMapping(value = "/reply/replyRegist.wow")
	public String replyRegist(ReplyVO reply){
		
		replyService.registReply(reply);
		return "성공";
	}
	
	@RequestMapping(value = "/reply/replyModify.wow")
	public Map<String,Object> replyModify(ReplyVO reply){
		Map<String, Object> map=new HashMap<String, Object>();
		System.out.println("리플"+reply);
		try{
			replyService.modifyReply(reply);
			map.put("result", true);
			map.put("msg","수정성공");
		} catch (BizAccessFailException e) {
			map.put("result", false);
			map.put("msg","당신은 댓글을 쓴 사람이 아닙니다.");
		}catch (BizNotFoundException e) {
			map.put("result", false);
			map.put("msg","댓글이 없습니다.");
		}
		return map;
	}
	
	@RequestMapping(value = "/reply/replyDelete.wow")
	public Map<String,Object> replyDelete(ReplyVO reply) throws BizAccessFailException, BizNotFoundException{
		replyService.removeReply(reply);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success",true);
		map.put("result", "삭제성공");
		return map;
	}
	
	
	
}