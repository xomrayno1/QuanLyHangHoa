package com.tampro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.service.ProductStockService;

@RestController
@RequestMapping("/api/v1/product-stock")
public class ProductStockAPI {
	@Autowired
	ProductStockService stockService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> getCountProductStock(@RequestParam("proId") int proId ,
															@RequestParam("invenId") int invenId ) {
		 
		try {
			long count = stockService.getCountProductInStockByProductAndInventory(proId, invenId);
			return new ResponseEntity<Long>(count,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
	}
}
