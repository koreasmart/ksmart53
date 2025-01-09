package ksmybatis.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ControllerAdvice(옵션) : 웹프로젝트의 예외처리 기능을 담당하는 어노테이션(Spring MVC 예외처리, 예외메세지를 뷰로 전달하는 기능)
 * basePackages = "ksmybatis.admin" : 특정 패키지 하위에 적용
 * annotation = "Controller.class, RestController.class" : 특정 어노테이션에만 적용
 * assignableTypes = "UserController.class" : 특정 클래스타입에만 적용
 */
@ControllerAdvice(basePackages = {"ksmybatis.admin"})
@Slf4j
public class AdminGlobalExceptionHandler {

	/**
	 * @ExceptionHandler(Exception.class) : 예외 클래스종류에 맞는 메소드를 매핑하는 어노테이션 
	 * @ResponseStatus(HttpStatus.필드(코드에 맞는 이름)) : 응답시 에러코드를 반환하는 어노테이션
	 * @return String 에러메세지를 전달하는 논리뷰 경로
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionAllHandler(Exception ex, HttpServletRequest request, Model model) {
		log.error("[url : {}] 500 error message : {}", request.getRequestURI(), ex.getStackTrace());
		model.addAttribute("homeUri", "/admin");
		return "error/500";
	}
	
	@ExceptionHandler({IllegalArgumentException.class, 
					   MethodArgumentTypeMismatchException.class, 
					   MissingServletRequestParameterException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String exceptionBadRequestHandler(Exception ex, HttpServletRequest request, Model model) {
		log.error("[url : {}] 400 error message : {}", request.getRequestURI(), ex.getMessage());
		model.addAttribute("homeUri", "/admin");
		return "error/400";
	}
}





























