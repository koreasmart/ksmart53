package ksmybatis.admin.login.dto;

import lombok.Data;

@Data
public class LoginHistory {
	private Integer loginNo;
	private String loginId;
	private String loginIp;
	private String loginDate;
}
