package ksmybatis.admin.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.member.dto.Member;
import ksmybatis.util.Pageable;

@Mapper
public interface LoginMapper {
	// 로그인 이력 총 갯수
	int getCntLoginHistory();
	
	// 로그인 이력
	List<Member> getLoginHistory(Pageable pageable);

	// 로그인 이력 삭제
	int removeLoginHistoryById(String memberId);
}
