package jp.gtf.taotao001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.gtf.taotao001.entity.UserEntity;
import jp.gtf.taotao001.repository.UserRepository;
import jp.gtf.taotao001.service.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControllr {

	@Autowired
	RegisterService registerService;

	@Autowired
	UserRepository userrepository;

	@RequestMapping(value = "/")
	public ModelAndView login(
			@RequestParam(value = "result", required = false) String result) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("result", result);

		return mav;

	}

	@RequestMapping(value = "/login-process", method = RequestMethod.POST)
	public ModelAndView loginProcess(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest servlet) {
		UserEntity entity = userrepository.findByEmail(email);
		if (entity == null) {
			return new ModelAndView("redirect:/?result=ERR_RECORD_OVERLAPPED");
		}
		if (email.equals(entity.getEmail())
				&& password.equals(entity.getPassword())) {
			HttpSession session = servlet.getSession(true);

			session.setAttribute("email", email);
			return new ModelAndView("redirect:/list");
		} else
		return new ModelAndView("redirect:/?result=NG_REQUEST_ERROR");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView userInfo(HttpServletRequest servlet) {
		HttpSession session = servlet.getSession(false);
		Object email = session.getAttribute("email");
		if (email == null) {
			// 未ログイン
			return new ModelAndView("redirect:/");
		} else {
			// select * from .. where userid = ?
			// データベースからユーザー詳細情報を取得する
			ModelAndView mav = new ModelAndView("list");
			UserEntity entity = userrepository.findByEmail(email);
			mav.addObject("entity", entity);
			return mav;
		}
	}
}