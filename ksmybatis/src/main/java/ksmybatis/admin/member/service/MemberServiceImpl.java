package ksmybatis.admin.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.login.mapper.LoginMapper;
import ksmybatis.admin.member.dto.Member;
import ksmybatis.admin.member.mapper.MemberMapper;
import ksmybatis.admin.order.dto.Order;
import ksmybatis.admin.order.mapper.OrderMapper;
import ksmybatis.admin.product.mapper.ProductMapper;
import ksmybatis.admin.vendor.mapper.VendorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("adminMemberService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;
	private final VendorMapper vendorMapper;
	private final LoginMapper loginMapper;
	
	@Override
	public List<Member> searchList(String searchCate, String searchValue, int listSize) {
		switch (searchCate) {
			case "id" 		-> searchCate = "m.mbr_id";
			case "name" 	-> searchCate = "m.mbr_name";
			case "grade" 	-> searchCate = "cc.comm_nm";
			case "email" 	-> searchCate = "m.mbr_email";		
		}
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCate", searchCate);
		searchMap.put("searchValue", searchValue);
		searchMap.put("listSize", listSize);
		
		List<Member> memberList = memberMapper.getSearchList(searchMap);
		
		return memberList;
	}
	
	@Override
	public void removeMember(String grade, String memberId) {
		
		switch (grade) {
			case "mbr_grd_2" -> {
				// 판매자
				// 1. 상품을 구매한 주문내역 조회
				List<Order> orderList = orderMapper.getOrderListBySellerId(memberId);
				if(orderList != null && orderList.size() > 0) {
					// 2. 상세 주문내역 삭제
					orderMapper.removeOrderBySellerId(memberId);
				}
				// 3. 상품 삭제
				productMapper.removeProductBySellerId(memberId);
				// 4. 판매처 삭제
				vendorMapper.removeVendorBySellerId(memberId);				
			}
			case "mbr_grd_3" -> {
				List<Integer> orderNumList = orderMapper.getOrderNumListByOrderId(memberId);
				// 구매자
				if(orderNumList != null && orderNumList.size() > 0) {
					// 1. 주문 상세 내역 삭제
					orderMapper.removeOrderItemByNum(orderNumList);
					// 2. 주문 내역 삭제
					orderMapper.removeOrderByOrderId(memberId);
				}
			}
		}
		
		// 관리자, 판매자, 구매자, 일반회원
		// 1. log history 삭제
		loginMapper.removeLoginHistoryById(memberId);
		// 2. 회원 탈퇴
		memberMapper.removeMemberById(memberId);
	}
	
	@Override
	public Map<String, Object> matchedMember(String memberId, String memberPw) {
		
		boolean isMatched = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		if(memberInfo != null) {
			String checkPw = memberInfo.getMemberPw();
			if(checkPw.equals(memberPw)) {
				isMatched = true;
				resultMap.put("memberInfo", memberInfo);
			}
		}
		resultMap.put("isMatched", isMatched);
		
		return resultMap;
	}
	
	@Override
	public void modifyMember(Member member) {
		memberMapper.modifyMember(member);		
	}
	
	@Override
	public Member getMemberInfoById(String memberId) {
		
		return memberMapper.getMemberInfoById(memberId);
	}
	
	@Override
	public void addMember(Member member) {
		int result = memberMapper.addMember(member);
	}
	
	@Override
	public boolean isMemberById(String memberId) {

		return memberMapper.isMemberById(memberId);
	}
	
	@Override
	public List<Map<String, Object>> getGradeList() {
		
		return memberMapper.getMemberGradeList();
	}
	
	@Override
	public List<Member> getMemberList() {
		
		List<Member> memberList = memberMapper.getMemberList();
				
		memberList.forEach(memberInfo -> {
			String gradeCode = memberInfo.getMemberGrade();
			switch (gradeCode) {
				case "mbr_grd_1" -> {
					memberInfo.setMemberGrade("관리자");
				}
				case "mbr_grd_2" -> memberInfo.setMemberGrade("판매자");
				case "mbr_grd_3" -> memberInfo.setMemberGrade("구매자");
			}
		});
		
		log.info("memberList : {}", memberList);
		
		
		return memberList;
	}
}







