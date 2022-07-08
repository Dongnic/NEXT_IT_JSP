package com.study.free.web;
import java.util.List;
import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.valid.Modify;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
@Controller
public class FreeBoardController {
	//process 하나당 @RequestMapping 1개
	// list view edit modify delete form regist
	
	@Inject
	IFreeBoardService freeBoardService;
	
	@Inject
	ICommCodeService codeService;
	
	@ModelAttribute("cateList")
	public List<CodeVO> cateList(){
		return codeService.getCodeListByParent("BC00");
	}
	// @Controller 제발 까먹지 말기, @RM, Postmapping / @ModelAttribute(), @RequestParam()
	
	// 매개변수로 온 거를 model에 담을때는 @ModelAttribute 사용
	@RequestMapping("/free/freeList.wow")
	public String freeList(Model model, @ModelAttribute("searchVO") FreeBoardSearchVO searchVO) {
		//model.addAttribute("searchVO", searchVO);
		
		List<FreeBoardVO> freeBoardList=freeBoardService.getBoardList(searchVO);
		model.addAttribute("freeBoardList", freeBoardList);
	 	
		return "free/freeList";
	}
	
	@RequestMapping("/free/freeView.wow")
	public String freeView(Model model, @RequestParam(required = false) int boNo) {
		try{
			FreeBoardVO freeBoard=freeBoardService.getBoard(boNo);
			freeBoardService.increaseHit(boNo);
			model.addAttribute("freeBoard", freeBoard);
			return "free/freeView";
		}catch (BizNotFoundException  | BizNotEffectedException  e){
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting(false,
					"NotFound or NotEffected", "글이없다 or조회수증가실패", "/free/freeList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	
	@RequestMapping("/free/freeEdit.wow")
	public String freeEdit(Model model, @RequestParam(required = false) int boNo) {
		try{
			FreeBoardVO freeBoard=freeBoardService.getBoard(boNo);
			model.addAttribute("freeBoard", freeBoard);
			return "free/freeEdit";
		}catch(BizNotFoundException e){
			ResultMessageVO resultMessageVO=new ResultMessageVO();
			resultMessageVO.messageSetting(false,
					"NotFound", "글이없다", "/free/freeList.wow", "목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	// @RM은 get,post 기타 등등 다 수용해요.
	// freeModify.wow를 post만 허용하게 할려면?
	//@RequestMapping(value = "/free/freeModify.wow", method = RequestMethod.POST)
	//@PostMapping("/free/freeModify.wow")
	@RequestMapping("/free/freeModify.wow")
	public String freeModify(Model model ,@ModelAttribute("freeBoard") @Validated(value = {Modify.class, Default.class}) FreeBoardVO freeBoard, BindingResult error) {
																		// 검사어노테이션                검사 결과는 검사대상객체 뒤
		//freeBoardVO boNo가 있는지, boPass,가 null이 아닌지, email형식인지, 전화번호형식, 비밀번호, 영문숫자조합인지 등을 검사해야된다.
		
		if(error.hasErrors()) {
			return "free/freeEdit";
		}
		
//		boolean hasNotError = modifyHasNotError(freeBoard);
//		if( !hasNotError) {// 문제가 있으면 입력을 안한거니까 재입력
//			return "free/freeEdit";
//		}
		
		
		// 이밑의 코드는 문제가 없을때
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		try{
			freeBoardService.modifyBoard(freeBoard);
			resultMessageVO.messageSetting(true,
					"MODIFY", "수정성공", "/free/freeList.wow", "목록으로");
		}catch (BizNotFoundException bnf){
			resultMessageVO.messageSetting(false,
					"NotFound", "수정실패", "/free/freeList.wow", "목록으로");
		}catch (BizNotEffectedException bne){
			resultMessageVO.messageSetting(false,
					"NotEffected", "수정실패", "/free/freeList.wow", "목록으로");
		}catch(BizPasswordNotMatchedException bpn){
			resultMessageVO.messageSetting(false,
					"비밀번호틀림", "비밀번호틀렸다 너. 수정실패", "/free/freeList.wow", "목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}	
	
	@PostMapping("/free/freeDelete.wow")
	public String freeDelete(Model model, @ModelAttribute("freeBoard") FreeBoardVO freeBoard) {
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		try{
			freeBoardService.removeBoard(freeBoard);
			resultMessageVO.messageSetting(true,
					"DELETE", "삭제성공", "/free/freeList.wow", "목록으로");
		}catch (BizNotFoundException bnf){
			resultMessageVO.messageSetting(false,
					"NotFound", "삭제실패", "/free/freeList.wow", "목록으로");
		}catch (BizNotEffectedException bne){
			resultMessageVO.messageSetting(false,
					"NotEffected", "삭제실패", "/free/freeList.wow", "목록으로");
		}catch(BizPasswordNotMatchedException bpn){
			resultMessageVO.messageSetting(false,
					"비밀번호틀림", "비밀번호틀렸다 너.  글쓴사람 아니지?", "/free/freeList.wow", "목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		
		return "common/message";
	}
	@RequestMapping("/free/freeForm.wow")
	public String freeForm(Model model, @ModelAttribute("freeBoard") FreeBoardVO freeBoard) {
		return "free/freeForm";
	}
	
	@PostMapping("/free/freeRegist.wow")
	public String freeRegist(Model model, @ModelAttribute("freeBoard") @Validated() FreeBoardVO freeBoard, BindingResult error) {
		ResultMessageVO resultMessageVO= new ResultMessageVO();
		if(error.hasErrors()) {
			return "free/freeForm";
		}
		
		try {
			freeBoardService.registBoard(freeBoard);
			resultMessageVO.messageSetting(true,
					"REGIST", "등록성공", "/free/freeList.wow", "목록으로");
		} catch (BizNotEffectedException e) {
			resultMessageVO.messageSetting(false,
					"NotEffected", "등록실패", "/free/freeList.wow", "목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}
	
	// freeBoard 데이터가 잘 왔는지 검사, 문제가 없을때 true
	private boolean modifyHasNotError(FreeBoardVO freeBoard) {
		if(freeBoard.getBoNo()==0) return false;
		if(freeBoard.getBoPass()== null || freeBoard.getBoPass().isEmpty()) return false;
		// 나머지 필드도 검사하고 싶은거 있으면 검사해서 return false;
		
		return true;
	}
	
	
	
	
	
	
	
}