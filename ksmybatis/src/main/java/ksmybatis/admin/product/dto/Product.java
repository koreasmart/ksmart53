package ksmybatis.admin.product.dto;

import ksmybatis.admin.vendor.dto.Vendor;
import lombok.Data;

@Data
public class Product {
	private String productCode;
	private String vendorCode;
	private String productName;
	private int productPrice;
	private String productRegDate;
	
	private Vendor vendorInfo;
}
