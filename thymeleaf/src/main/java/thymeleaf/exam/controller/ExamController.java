package thymeleaf.exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleaf.exam.dto.User;
import thymeleaf.exam.service.ExamService;

/* @ (애노테이션) : 컴파일러한테 정보를 전달해주는 키워드 */
/**
 * @Controller : 클라이언트 주소 요청과 응답을 수행하는 계층
 */
@Controller
@RequestMapping("/exam")
public class ExamController {
	
	/**
	 * DI(Dependency Injection) : 의존성주입
	 * 1.필드 주입방식
	 * @Autowired
	 * private ExamService examService;
	 * 
	 * 2.수정자 메소드 주입방식
	 * private ExamService examService;
	 * @Autowired
	 * public void setExamService(ExamService examService) {
	 *	  this.examService = examService;
	 * }
	 * 
	 * 3.생성자 메소드 주입방식
	 * 순환참조 방지
	 */
	
	private final ExamService examService;
	
	public ExamController(ExamService examService) {
		this.examService = examService;
	}
	
	@GetMapping("/exam5")
	public String exam5(Model model) {
		
		User userInfo = examService.getUserInfo();
		List<User> userList = examService.getUserList();
		
		model.addAttribute("title", "exam5");
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("userList", userList);
		
		return "exam/exam5";
	}
	
	@GetMapping("/exam4")
	public String exam4(Model model) {
		
		User userInfo = examService.getUserInfo();
		List<User> userList = examService.getUserList();
		
		model.addAttribute("title", "exam4");
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("userList", userList);
		
		return "exam/exam4";
	}
	
	@GetMapping("/exam3")
	public String exam3(Model model) {
		User userInfo = examService.getUserInfo();
		List<User> userList = examService.getUserList();
		
		model.addAttribute("title", "exam3");
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("userList", userList);
		
		return "exam/exam3";
	}
	
	@GetMapping("/exam2")
	public String exam2(Model model) {
		
		var userList = examService.getUserList();
		model.addAttribute("title", "exam2");
		model.addAttribute("userList", userList);
		
		return "exam/exam2";
	}
	
	@GetMapping("/exam1")
	public String exam1(Model model) {

		User userInfo = examService.getUserInfo();
		System.out.println(examService);
		System.out.println(userInfo);
		
		model.addAttribute("title", "exam1");
		model.addAttribute("userInfo", userInfo);
		
		return "exam/exam1";
	}
}




