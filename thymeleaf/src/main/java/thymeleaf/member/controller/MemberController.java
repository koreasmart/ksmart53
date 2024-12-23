package thymeleaf.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberController {
	
	@GetMapping("/member/{userId}")
	public String getMemberDetail(@PathVariable(name="userId") String memberId) {
		System.out.println("memberId : " + memberId);
		//return "member/memberDetail";
		return "user/userDetail";
	}

}
