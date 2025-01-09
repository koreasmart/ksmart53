package ksmybatis.admin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Controller  사용자 http 요청과 응답을 수행하는 계층
 */
@Controller
@RequestMapping("/admin")
public class HomeController {

	@GetMapping(value={"", "/"})
	public String adminHome(Model model) {
		
		model.addAttribute("title", "관리자 메인화면");
		
		return "admin/index";
	}
}
