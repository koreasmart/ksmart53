package ksmybatis.admin.vendor.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VendorMapper {

	// 판매처 삭제
	int removeVendorBySellerId(String sellerId);
}
