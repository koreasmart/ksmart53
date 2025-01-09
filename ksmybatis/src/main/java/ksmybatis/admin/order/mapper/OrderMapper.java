package ksmybatis.admin.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.order.dto.Order;

@Mapper
public interface OrderMapper {
	// 주문이력 삭제
	int removeOrderByOrderId(String orderId);
	
	// 주문번호 상세주문 삭제
	int removeOrderItemByNum(List<Integer> orderNumList);
	
	// 구매자가 구매한 주문번호 조회
	List<Integer> getOrderNumListByOrderId(String orderId);
	
	// 판매자가 등록한 상품의 구매이력 삭제
	int removeOrderBySellerId(String sellerId);

	// 판매자가 등록한 상품의 구매이력 조회
	List<Order> getOrderListBySellerId(String sellerId);
	
	
}
