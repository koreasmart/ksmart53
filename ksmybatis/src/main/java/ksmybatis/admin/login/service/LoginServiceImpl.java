package ksmybatis.admin.login.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.login.mapper.LoginMapper;
import ksmybatis.admin.member.dto.Member;
import ksmybatis.util.PageInfo;
import ksmybatis.util.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	private final LoginMapper loginMapper; 

	@Override
	public PageInfo<Member> getLoginHistory(Pageable pageable) {
		int rowCnt = loginMapper.getCntLoginHistory();
		List<Member> loginList = loginMapper.getLoginHistory(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}

}




