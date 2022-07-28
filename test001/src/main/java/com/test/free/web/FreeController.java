package com.test.free.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.free.dao.IFreeBoardDao;
import com.test.free.vo.FreeBoardVO;

@Controller
public class FreeController {

	@Inject
	IFreeBoardDao freeBoardDao;
	
	@RequestMapping("/free/freeList.wow")
	public String freeList(Model model) {
		List<FreeBoardVO> freeBoardList=freeBoardDao.getBoardList();
		model.addAttribute("freeBoardList",freeBoardList);
		return "free/freeList";
	}
}
