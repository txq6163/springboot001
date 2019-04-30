package jp.gtf.taotao001.controller;

import jp.gtf.taotao001.repository.UserRepository;
import jp.gtf.taotao001.service.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

	@Autowired
	RegisterService registerService;

	@Autowired
	UserRepository userrepository;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(
			@RequestParam(value = "result", required = false) String result) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		mav.addObject("result", result);
		return mav;

	}

	@RequestMapping(value = "/register-process", method = RequestMethod.POST)
	public ModelAndView registerProcess(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "password-confirm", required = true) String passwordConfirm) {

		// try {
		// isEmpty(userName);
		// isEmpty(password);
		// isEmpty(passwordConfirm);
		// } catch (Exception e) {
		if (!password.equals(passwordConfirm)) {
			return new ModelAndView(
					"redirect:/register?result=NG_REQUEST_ERROR");
			// }
		}
		if (userrepository.findByEmail(email) != null) {
			return new ModelAndView(
					"redirect:/register?result=ERR_RECORD_OVERLAPPED");
		}
		registerService.registerUser(userName, email, password);
		return new ModelAndView("redirect:/");
	}

	// private static void isEmpty(String v) throws Exception {
	// if (v == null ? true : v.isEmpty())
	// throw new RuntimeException("該当項目は必須です。");
	//
	// }
}
