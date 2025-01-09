package ksmybatis.admin.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmybatis.admin.product.dto.Product;
import ksmybatis.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

	private final ProductService productService; 
	
	@GetMapping("/list")
	public String productListView(Model model) {
		
		List<Product> productList = productService.getProductList();
		log.info("productList : {}", productList);
		
		model.addAttribute("title", "상품목록");
		model.addAttribute("productList", productList);
		
		return "admin/product/productList";
	}
}




