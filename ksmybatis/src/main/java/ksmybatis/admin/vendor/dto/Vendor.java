package ksmybatis.admin.vendor.dto;

import java.util.List;

import ksmybatis.admin.product.dto.Product;
import lombok.Data;

@Data
public class Vendor {

	private String vendorCode; 
	private String vendorMemberId; 
	private String vendorBrno; 
	private String vendorName; 
	private String vendorAddr; 
	private String vendorDetailAddr; 
	private String vendorZip; 
	private String vendorTelno;
	
	private List<Product> productList;
	
}
