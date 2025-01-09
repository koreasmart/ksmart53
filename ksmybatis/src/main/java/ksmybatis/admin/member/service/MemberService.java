package ksmybatis.admin.member.service;

import java.util.List;
import java.util.Map;

import ksmybatis.admin.member.dto.Member;

public interface MemberService {
	// 회원목록 검색조회
	List<Member> searchList(String searchCate, String searchValue, int listSize);
	
	// 회원탈퇴
	void removeMember(String grade, String memberId);
	
	// 특정회원 검증
	Map<String, Object> matchedMember(String memberId, String memberPw);
	
	// 특정회원수정
	void modifyMember(Member member);
	
	// 특정회원 조회
	Member getMemberInfoById(String memberId);
	
	// 회원등록
	void addMember(Member member);
	
	// 중복아이디체크
	boolean isMemberById(String memberId);
	
	// 회원등급조회
	List<Map<String, Object>> getGradeList();

	// 회원목록조회
	List<Member> getMemberList();
}
