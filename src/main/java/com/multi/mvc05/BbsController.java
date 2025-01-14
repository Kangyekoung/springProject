package com.multi.mvc05;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 싱글톤 + 컨트롤러 등록
public class BbsController {

	@Autowired
	BbsDAO dao;
	
	@Autowired
	ReplyDAO dao2;
	
	@RequestMapping("one")
	public void one(BbsDTO2 dto, Model model) throws Exception {
		//one요청했을 때 views에 넣고 싶은 처리 내용을 다 써줘야한다.
		//bbs 상세페이지 + reply 댓글리스트 
		System.out.println(dto);
		BbsDTO2 bag = dao.one(dto);
		List<ReplyDTO> list = dao2.list(dto.getId());
		
		System.out.println(list.size());
		
		
		model.addAttribute("bag", bag); //Object(큰) <-- BbsDTO2
		model.addAttribute("list", list);
	}
	
	@RequestMapping("list")
	public void list(Model model) throws Exception {
		//dao를 이용해서 여러개를 받아서 가지고 와주세요.
		List<BbsDTO2> list = dao.list();
		System.out.println(list.size());
		//views/list.jsp까지 넘어가야 함.==>Model 
		//model을 이용해서 검색결과인 list를 list.jsp까지 넘기자.!
		model.addAttribute("list", list);
	}
	
	@RequestMapping("jsonbbs")
	@ResponseBody // List<BbsDTO2>  ---> [json, json...]
	public List<BbsDTO2> jsonbbs() throws Exception {
		//dao를 이용해서 여러개를 받아서 가지고 와주세요.
		List<BbsDTO2> list = dao.list();
		
		return list;
	}
	
	
	// 요청하나당 함수하나.
	@RequestMapping("insert2")
	public void insert2(BbsDTO2 bag, Model model) {
		System.out.println(bag);
		// db처리 --> views/insert2.jsp결과를 출력해주세요.
		int result = dao.insert(bag);
		System.out.println(result);
		// result는 views/아래까지 가지고 가야하는 속성값이야 설정!!!
		// views까지 데이터를 넘길 수 있는 객체 필요(Model)
		// 함수(Model model)써놓으면 스프링이 model객체를 만들어서 넣어준다.
		model.addAttribute("result", result);
	}

	@RequestMapping("update2")
	public String update2(BbsDTO2 bag) {
		int result = dao.update(bag);
		// views에 update2.jsp를 만드세요.
		if (result == 1) {
			return "redirect:bbs.jsp"; // views/ok.jsp
		} else {
			return "no"; // views/no.jsp
		}
	}

	@RequestMapping("delete2")
	public void delete2(BbsDTO2 bag, Model model) {
		// bag에 넣고
		// dao를 이용해서 db처리하고
		int result = dao.delete(bag);
		// views/delete2.jsp를 만들어서 결과를 출력
		model.addAttribute("result", result);
	}

}
