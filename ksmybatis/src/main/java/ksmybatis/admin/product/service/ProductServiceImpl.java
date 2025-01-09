package ksmybatis.admin.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.product.dto.Product;
import ksmybatis.admin.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;
	
	@Override
	public List<Product> getProductList() {
		
		return productMapper.getProductList();
	}
}










