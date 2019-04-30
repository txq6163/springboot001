package jp.gtf.taotao001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListController {

	@RequestMapping(value = "/list-process")
	public ModelAndView register() {

		return new ModelAndView("list");
	}
	@RequestMapping(value = "/loginOut")
	public ModelAndView loginOutPage(){
		return new ModelAndView("loginOut");
	}
}