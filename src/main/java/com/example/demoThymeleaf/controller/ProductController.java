package com.example.demoThymeleaf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoThymeleaf.model.Product;
import com.example.demoThymeleaf.model.ResponseObject;
import com.example.demoThymeleaf.repo.ProductRepository;



@RestController
@RequestMapping(path="/api/v1/products")
public class ProductController {

	@Autowired
	private ProductRepository reposity;
	
	@GetMapping("")
	public List<Product> getAllProduct(){
		return reposity.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findId(@PathVariable Long id){
		Optional<Product> foundProduct=reposity.findById(id);
		return foundProduct.isPresent()?ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query product suscess", foundProduct)):
			
			ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Cannot find id "+id, ""));
		
	}
	
	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
		List<Product> foundProduct=reposity.findByProductName(newProduct.getProductName().trim());
		if(foundProduct.size()>0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed","product name already taken", ""));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","insert product suscess", reposity.save(newProduct)));
		
	}
	
	@PostMapping("/insertAuto")
	public ResponseEntity<ResponseObject> insertProductAuto(){
		Product newProduct=new Product("auto",2024,10,"");
		List<Product> foundProduct=reposity.findByProductName(newProduct.getProductName().trim());
		if(foundProduct.size()>0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed","product name already taken", ""));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","insert product suscess", reposity.save(newProduct)));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
		Product updateP=reposity.findById(id).map(
product -> {
	product.setProductName(newProduct.getProductName());
	product.setPrice(newProduct.getPrice());
	product.setYear(newProduct.getYear());
	return reposity.save(product);
}
				).orElseGet(()->{
					newProduct.setId(id);
					return reposity.save(newProduct);
				});
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","update suscess", updateP));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
		boolean exists=reposity.existsById(id);
		
		if(exists) {
			reposity.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","delete suscess",""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed","cannot find product delete",""));	 
	}
}
