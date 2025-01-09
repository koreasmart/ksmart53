package ksmybatis.admin.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmybatis.admin.member.dto.Member;
import ksmybatis.admin.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/member")
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(@Qualifier("adminMemberService") MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/searchList")
	public String searchListView(@RequestParam(value="searchCate", required = false, defaultValue = "id") String searchCate,
								 @RequestParam(value="searchValue") String searchValue,
								 @RequestParam(value="listSize", required = false, defaultValue = "5") int listSize,
								 Model model) {
		
		log.info("searchCate: {}, searchValue: {}, listSize: {}", searchCate, searchValue, listSize);
		
		List<Member> memberList = memberService.searchList(searchCate, searchValue, listSize);
		
		model.addAttribute("title", "회원목록");
		model.addAttribute("searchCate", searchCate);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("listSize", listSize);
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberList";
	}
	
	@PostMapping("/remove")
	public String removeMember(@RequestParam(value="memberId") String memberId,
							   @RequestParam(value="memberPw") String memberPw,
							   RedirectAttributes reAttr) {
		
		String viewName = "redirect:/admin/member/remove";
		
		Map<String, Object> resultMap =  memberService.matchedMember(memberId, memberPw);
		boolean isMatched = (boolean) resultMap.get("isMatched");
		if(isMatched) {
			Member memberInfo = (Member) resultMap.get("memberInfo");
			String grade = memberInfo.getMemberGrade();
			memberService.removeMember(grade, memberId);
			viewName = "redirect:/admin/member/list";
		}else {
			reAttr.addAttribute("memberId", memberId);
			reAttr.addAttribute("msg", "회원의 정보가 일치하지 않습니다.");
			reAttr.addFlashAttribute("message", "회원의 정보가 일치하지 않습니다. flashMap");
		}
		
		return viewName;
	}
	
	@GetMapping("/remove")
	public String removeMemberView(Model model, 
								   @RequestParam(value="memberId") String memberId,
								   @RequestParam(value="msg", required = false) String msg,
								   @ModelAttribute("message") String message) {
		
		model.addAttribute("title", "회원탈퇴");
		model.addAttribute("memberId", memberId);
		if(msg != null) model.addAttribute("msg", msg);
		
		return "admin/member/removeMember";
	}
	
	@PostMapping("/modify")
	public String modifyMember(Member member,
							   RedirectAttributes reAttr) {
		
		memberService.modifyMember(member);
		
		reAttr.addAttribute("memberId", member.getMemberId());
		
		return "redirect:/admin/member/modify";
	}
	
	@GetMapping("/modify")
	public String modifyMemberView(@RequestParam(name="memberId") String memberId,
								   Model model) {
		var gradeList = memberService.getGradeList();
		Member memberInfo = memberService.getMemberInfoById(memberId);
		
		model.addAttribute("title", "회원수정");
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("memberInfo", memberInfo);
		
		return "admin/member/modifyMember";
	}
	
	@PostMapping("/write")
	public String addMember(Member member) {

		log.info("member : {}", member);
		memberService.addMember(member);
		
		return "redirect:/admin/member/list";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="memberId") String memberId) {
		boolean isDuplicate = false;
		
		log.info("memberId: {}", memberId);
		isDuplicate = memberService.isMemberById(memberId);
		
		return isDuplicate;
	}
	
	@GetMapping("/write")
	public String addMemberView(Model model) {
		
		var gradeList = memberService.getGradeList();
		
		model.addAttribute("title", "회원가입");
		model.addAttribute("gradeList", gradeList);
		
		return "admin/member/addMember";
	}

	@GetMapping("/list")
	public String memberListView(Model model) {
		
		List<Member> memberList = memberService.getMemberList();
		
		model.addAttribute("title", "회원목록");
		model.addAttribute("memberList", memberList);
		
		
		return "admin/member/memberList";
	}
}
