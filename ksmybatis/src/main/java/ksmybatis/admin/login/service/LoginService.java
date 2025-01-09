package ksmybatis.admin.login.service;

import ksmybatis.admin.member.dto.Member;
import ksmybatis.util.PageInfo;
import ksmybatis.util.Pageable;

public interface LoginService {

	// 로그인 이력
	PageInfo<Member> getLoginHistory(Pageable pageable);
}
