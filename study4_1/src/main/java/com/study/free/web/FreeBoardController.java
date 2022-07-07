package com.study.free.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

@Controller
public class FreeBoardController {
	
	@Inject
	IFreeBoardService freeBoardService;
	
	@Inject
	ICommCodeService codeService;
	
	// 중복 되는 부분 이 코드로 하면 됨
	@ModelAttribute("cateList")
	public List<CodeVO> cateList(){
		return codeService.getCodeListByParent("BC00");
	}
	
	// 매개변수에 파라미터 타입에 맞춰서 세팅하면 알아서 객체생성까지 해줌(변수, 객체 둘 다 가능)
	// 하지만 지역변수로 끝나기 때문에(아래 searchVO)
	// setAttribute or 매개변수에 @ModelAttribute("searchVO") 사용 
	@RequestMapping("/free/freeList.wow")
	public String freeList(Model model, @ModelAttribute("searchVO") FreeBoardSearchVO searchVO) {
		
		List<FreeBoardVO> freeBoardList=freeBoardService.getBoardList(searchVO); 
		model.addAttribute("freeBoardList", freeBoardList);
		
		return "free/freeList";
	}
	// @RequestParam(required = false) => boNo 파라미터가 있을수도 있고 없을 수도 있기때문에 
	// false로 설정하면 없어도 오류가 안나며, true로 해주는게 맞음 
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
	// @RequestMapping = @RM 
	// @RM은 get, post 기타 등등 다 수용한다.
	// 만약 freeModify.wow를 post방식으로만 허용하게 하려면?
	// @RM 대신 @PostMapping or RequestMapping("/free/freeModify.wow", method = RequestMethod.POST) 사용
																			// 뒤에 , RequestMethod.GET하면 추가 
	
	@PostMapping("/free/freeModify.wow")
	public String freeModify(Model model, @ModelAttribute("freeBoard") FreeBoardVO freeBoard) {
		
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
	
	@RequestMapping("/free/freeDelete.wow")
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
	public String freeForm(Model model) {
		return "free/freeForm";
	}
	
	@RequestMapping("/free/freeRegist.wow")
	public String freeRegist(Model model, @ModelAttribute("freeBoard") FreeBoardVO freeBoard) {

		ResultMessageVO resultMessageVO= new ResultMessageVO();
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
	
}
