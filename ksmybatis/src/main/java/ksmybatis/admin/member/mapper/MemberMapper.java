package ksmybatis.admin.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.member.dto.Member;

@Mapper
public interface MemberMapper {
	// 회원목록 검색조회
	List<Member> getSearchList(Map<String, Object> searchMap);
	
	// 특정회원 탈퇴
	int removeMemberById(String memberId);
	
	// 특정회원 수정
	int modifyMember(Member member);
	
	// 특정회원 조회
	Member getMemberInfoById(String memberId);
	
	// 회원등록
	int addMember(Member member);
	
	// 중복아이디 회원조회
	boolean isMemberById(String memberId);

	// 회원등급조회
	List<Map<String, Object>> getMemberGradeList();
	
	// 회원목록조회
	List<Member> getMemberList();
	
}
