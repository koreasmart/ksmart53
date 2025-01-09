package ksmybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GrobalExceptionHandler {

	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String exceptionNoResourceFoundHandler(NoResourceFoundException ex, HttpServletRequest request, Model model) {
		String errorUri = request.getRequestURI();
		String homeUri = "/";
		if(errorUri.indexOf("/admin") > -1) homeUri = "/admin";
		model.addAttribute("homeUri", homeUri);
		log.error("[url : {}] 404 error message : {}", request.getRequestURI(), ex.getMessage());
		return "error/404";
	}
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class, 
		   			   MethodNotAllowedException.class})
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public String exceptionNotSupportedHandler(Exception ex, HttpServletRequest request, Model model) {
		String errorUri = request.getRequestURI();
		String homeUri = "/";
		if(errorUri.indexOf("/admin") > -1) homeUri = "/admin";
		model.addAttribute("homeUri", homeUri);
		log.error("[url : {}] 405 error message : {}", request.getRequestURI(), ex.getMessage());
		return "error/405";
	}
	
}
