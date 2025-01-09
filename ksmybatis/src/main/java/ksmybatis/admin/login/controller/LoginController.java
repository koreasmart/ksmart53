package ksmybatis.admin.login.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ksmybatis.admin.login.service.LoginService;
import ksmybatis.admin.member.dto.Member;
import ksmybatis.admin.member.service.MemberService;
import ksmybatis.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

	private final LoginService loginService;
	private final MemberService memberService;
	
	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/admin/login";
	}
	
	@PostMapping("/admin/login/loginPro")
	public String loginProcess(@RequestParam(value="memberId") String memberId,
							   @RequestParam(value="memberPw") String memberPw,
							   RedirectAttributes reAttr,
							   HttpSession session) {
		String viewName = "redirect:/admin/login";
				
		Map<String, Object> resultMap =  memberService.matchedMember(memberId, memberPw);
		boolean isMatched = (boolean) resultMap.get("isMatched");
		if(isMatched) {
			Member memberInfo = (Member) resultMap.get("memberInfo");
			String memberGrade = memberInfo.getMemberGrade();
			String memberName = memberInfo.getMemberName();
			
			session.setAttribute("SID", memberId);
			session.setAttribute("SNAME", memberName);
			session.setAttribute("SGRD", memberGrade);
			
			viewName = "redirect:/admin";
		}else {
			reAttr.addAttribute("msg", "회원의 정보가 일치하지 않습니다.");
		}
		
		return viewName;
	}
	
	@GetMapping("/admin/login")
	public String loginView(Model model, @RequestParam(value="msg", required = false) String msg) {
		
		model.addAttribute("title", "로그인");
		if(msg != null) model.addAttribute("msg", msg);
		
		return "admin/login/loginView";
	}
	
	@GetMapping("/admin/login/history")
	public String loginHistoryView(Pageable pageable, Model model) {
		
		var pageInfo = loginService.getLoginHistory(pageable);
		
		List<Member> loginList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		log.info("loginList: {}", loginList);
		
		model.addAttribute("title", "로그인이력");
		model.addAttribute("loginList", loginList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		return "admin/login/loginHistory";
	}
}











