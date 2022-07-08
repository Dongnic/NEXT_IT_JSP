package com.study.member.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.valid.Step1;
import com.study.common.valid.Step2;
import com.study.common.valid.Step3;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberVO;

@Controller
public class MemberJoinController {
	
	@Inject
	IMemberService memberService;

	@Inject
	ICommCodeService codeService;
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList(){
		return codeService.getCodeListByParent("JB00");
	}
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbyList(){
		return codeService.getCodeListByParent("HB00");
	}
	
	@RequestMapping("/join/step1.wow")
	public String step1(@ModelAttribute("member") MemberVO member) {
		System.out.println("member1 : "+member);
		return "join/step1";
	}
	
	@RequestMapping("/join/step2.wow")
	public String step2(@ModelAttribute("member") @Validated(value = {Step1.class}) MemberVO member, BindingResult error) {
		System.out.println("member2 : "+member);
		if(error.hasErrors()) {
			return "join/step1";
		}
		return "join/step2";
	}
	
	@RequestMapping("/join/step3.wow")
	public String step3(@ModelAttribute("member") @Validated(value = {Step2.class}) MemberVO member, BindingResult error) {
		System.out.println("member3 : "+member);
		if(error.hasErrors()) {
			return "join/step2";
		}
		return "join/step3";
	}
	
	@RequestMapping("/join/regist.wow")
	public String regist(@ModelAttribute("member") @Validated(value = {Step3.class}) MemberVO member, BindingResult error) {
		
		System.out.println("member4 : "+member);
		if(error.hasErrors()) {
			return "join/step3";
		}
		return "common/message";
	}
	
	@RequestMapping("/join/cancel")
	public String cancel(@ModelAttribute("member") MemberVO member) {
		return "redirect:/"+"";
	}
	
}
