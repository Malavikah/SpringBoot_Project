package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		if( recMerchant.isPresent())
		{
			Merchant m= recMerchant.get();
			m.getProducts().add(product); //adding Product for Merchant
			product.setMerchant(m);  //assigning Merchant for Product
			merchantDao.updateMerchant(m);
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}	
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int merchant_id) {
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		if( recMerchant.isPresent())
		{
			Merchant m= recMerchant.get();
			m.getProducts().add(product); //adding Product for Merchant
			merchantDao.updateMerchant(m);
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}	
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if( recProduct.isPresent())
		{
			structure.setData(recProduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}	
		throw new IdNotFoundException();
	}	
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		if( recMerchant.isPresent())
		{
			structure.setData(dao.findByMerchantId(merchant_id));
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}	
		throw new IdNotFoundException();
	}	
		
}
