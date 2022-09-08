package com.study.mypage.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;
import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.util.StudyAttachUtils;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizAccessFailException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberVO;
import com.study.reply.service.IReplyService;
import com.study.reply.vo.ReplyVO;

@Controller
public class MypageController {

	@Inject
	IMemberService memberService;

	@Inject
	ICommCodeService codeService;

	@Inject
	StudyAttachUtils studyAttachUtils;
	@Inject
	IReplyService replyService;
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList() {
		return codeService.getCodeListByParent("JB00");
	}

	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList() {
		return codeService.getCodeListByParent("HB00");
	}

	@RequestMapping("/mypage/info.wow")
	public String info(Model model, HttpSession session,HttpServletRequest req) { 
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		// 여기까지 오기전에 Interceptor가 user를 체크해주기 때문에 아래 구문은 필요없음 7/12
//		if(user==null) { 
//		return "redirect:"+req.getContextPath()+"/login/login.wow";
//		}
		try {
			MemberVO member = memberService.getMember(user.getUserId());
			model.addAttribute("member", member);
			return "mypage/info";
		} catch (BizNotFoundException e) {
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "회원 없음", "회원이 없습니다", "/", "홈으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}

	@RequestMapping("/mypage/edit.wow")
	public String edit(Model model, HttpSession session,HttpServletRequest req) {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
//		if(user==null) { 
//		return "redirect:"+req.getContextPath()+"/login/login.wow";
//		}
		try {
			MemberVO member = memberService.getMember(user.getUserId());
			model.addAttribute("member", member);
			return "mypage/edit";
		} catch (BizNotFoundException e) {
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "회원 없음", "회원이 없습니다", "/", "홈으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	@RequestMapping("/ana/ana.wow")
	public String goana() {
		
		return "ana/ana";
	}
	@RequestMapping("/mypage/modify.wow")
	public String modify(Model model, HttpSession session, HttpServletRequest req, @ModelAttribute("member") MemberVO member) throws IOException, BizAccessFailException {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
//		if(user==null) { 
//		return "redirect:"+req.getContextPath()+"/login/login.wow";
//		}
		System.out.println(member);
		MultipartFile imgfile = member.getImgFile();
		if(!imgfile.isEmpty()) {
			System.out.println("imgFile -------- "+imgfile);
			String delfilename = req.getParameter("delfile");
			String memImg = studyAttachUtils.getAttachByProfileImage(imgfile, "userProfileImage", delfilename); 
			member.setMemImg(memImg);
		}else {
			member.setMemImg(req.getParameter("delfile"));
		}
		try {
			memberService.modifyMember(member);
			user.setUserName(member.getMemName());
			user.setUserPass(member.getMemPass());
			user.setUserImg(member.getMemImg());
			// reply에도 img 업데이트
			ReplyVO reply = new ReplyVO();
			reply.setReMemImg(member.getMemImg());
			reply.setReMemId(member.getMemId());
			replyService.changeReply(reply);
			
			session.setAttribute("USER_INFO", user);

			return "redirect:" + "info.wow";
		} catch (BizNotFoundException e) {
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "내 정보 없음", "내 정보가 없습니다", "/", "홈으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		} catch (BizNotEffectedException e) {
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.messageSetting(false, "내 정보 수정실패", "내정보 수정 실패했습니다", "/", "홈으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}

	}

}