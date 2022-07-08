package com.study.mypage.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberVO;

@Controller
public class MypageController {

	@Inject
	IMemberService memberService;

	@Inject
	ICommCodeService codeService;

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
		if(user==null) { 
		return "redirect:"+req.getContextPath()+"/login/login.wow";
		}
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
		if(user==null) { 
		return "redirect:"+req.getContextPath()+"/login/login.wow";
		}
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

	@RequestMapping("/mypage/modify.wow")
	public String modify(Model model, HttpSession session, HttpServletRequest req, @ModelAttribute("member") MemberVO member) {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		if(user==null) { 
		return "redirect:"+req.getContextPath()+"/login/login.wow";
		}
		try {
			memberService.modifyMember(member);

			user.setUserName(member.getMemName());
			user.setUserPass(member.getMemPass());
			session.setAttribute("USER_INFO", user);

			return "redirect:" + "/";
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