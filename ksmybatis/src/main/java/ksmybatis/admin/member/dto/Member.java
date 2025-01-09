package ksmybatis.admin.member.dto;

import java.util.List;

import ksmybatis.admin.login.dto.LoginHistory;
import lombok.Data;

/**
 * @Data : @Getter + @Setter + @ToString
 */
@Data
public class Member {

	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberGrade;
	private String memberGrdNum;
	private String memberAddr;
	private String memberDetailAddr;
	private Integer memberZip;
	private String memberTelNo;
	private String memberEmail;
	private String memberRegDate;
	
	private List<LoginHistory> loginHistory;
	
}












