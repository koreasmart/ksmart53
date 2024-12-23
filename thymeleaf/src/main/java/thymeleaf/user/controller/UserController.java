package thymeleaf.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thymeleaf.exam.dto.User;
import thymeleaf.exam.service.ExamService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 실습 : 상세페이지를 완성하시오.
	 * User userInfo = userService.getUserInfoById(userId)
	 * userDetail.html 상세보기 화면 완성
	 * memberDetail.html 상세보기 화면 완성
	 */
	
	private final ExamService examService;
	
	public UserController(ExamService examService) {
		this.examService = examService;
	}

	// localhost/user/detail?userId=id001
	/**
	 * @RequestParam : 쿼리파라미터의 값을 바인딩하는 어노테이션
	 * 				   옵션: name="파라미터 키" required="필수여부 boolean" defaultValue="파라미터의 값이 없을경우 기본값설정"
	 * @param String userId => 파라미터의 키와 매개변수명과 일치하면 값을 바인딩
	 * @param User userInfo => 객체 내부의 필드명과 파라미터의 키와 일치하면 set메소드를 통해 필드에 할당(커맨드객체)
	 */
	@GetMapping("/detail")
	public String getUserDetail(@RequestParam(name="userId", required = false, defaultValue = "id001") String memberId,
								String userId,
								User userInfo) {
		System.out.println("memberId : " + memberId);
		System.out.println("userId : " + userId);
		System.out.println("userInfo : " + userInfo);
		return "user/userDetail";
	}
}












