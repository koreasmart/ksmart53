package thymeleaf.exam.service;

import java.util.List;

import thymeleaf.exam.dto.User;

public interface ExamService {
	
	// 회원정보 
	public User getUserInfo();
	
	// 회원목록
	List<User> getUserList();
}
