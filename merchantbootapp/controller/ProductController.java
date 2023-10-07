package org.jsp.merchantbootapp.controller;

import java.util.List;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.MerchantService;
import org.jsp.merchantbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, @PathVariable int merchant_id) {
		return service.saveProduct(product, merchant_id);
	}
	
	@PutMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product, @PathVariable int merchant_id) {
		return service.updateProduct(product, merchant_id);
	}
	
	@GetMapping("/products/by-id/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@GetMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(@PathVariable int merchant_id) {
		return service.findByMerchantId(merchant_id);
	}
	
}
