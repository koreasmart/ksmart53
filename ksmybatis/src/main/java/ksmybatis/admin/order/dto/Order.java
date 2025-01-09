package ksmybatis.admin.order.dto;

import lombok.Data;

@Data
public class Order {
	private Integer orderNo;
	private String 	orderItem;
	private String 	orderId;
	private String 	orderProductCode;
	private Integer orderAmount;
	private String  orderRegDate;
}
