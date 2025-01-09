package ksmybatis.admin.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.product.dto.Product;

@Mapper
public interface ProductMapper {

	// 상품삭제
	int removeProductBySellerId(String sellerId);
	
	// 상품조회
	List<Product> getProductList();
}
