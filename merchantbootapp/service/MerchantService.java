package org.jsp.merchantbootapp.service;

import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.jsp.merchantbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant = dao.saveMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("Message registered successfully"+merchant.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant = dao.updateMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("Message updated successfully" + merchant.getId());
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = dao.findById(id);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if (recMerchant.isPresent()) 
		{
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		boolean deleted = dao.deleteById(id);
		ResponseStructure<String> structure=new ResponseStructure<>();
		if(deleted) {
			structure.setData("Merchant Deleted");
			structure.setMessage("Merchant not Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.CREATED);
		}
		structure.setData("Merchant Deleted");
		structure.setMessage("Merchant not Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		Optional<Merchant> recMarchant = dao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		
		if (recMarchant.isPresent()) {
			structure.setData(recMarchant.get());
			structure.setMessage("Merchant Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}
	
}


