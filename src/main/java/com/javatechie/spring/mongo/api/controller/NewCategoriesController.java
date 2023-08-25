package com.javatechie.spring.mongo.api.controller;

import java.io.Console;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.mongo.api.model.NewCategoryDTO;
import com.javatechie.spring.mongo.api.repository.NewCategoriesRepository;

@RestController
public class NewCategoriesController {
	
	//Tạo đối tượng để sử dụng spring data jpa mongo
	@Autowired
	private NewCategoriesRepository newCategoryRepo;
	
	//Get all category
	@GetMapping("/new-categories")
	public ResponseEntity<?> getAllNewCategory() {
		List<NewCategoryDTO> newCat = newCategoryRepo.findAll();
		
		if(newCat.size() > 0) {
			return new ResponseEntity<List<NewCategoryDTO>>(newCat, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Not Categories Available", HttpStatus.NOT_FOUND);
		}
	}
	
	//create new category
	@PostMapping("/new-categories")
	public ResponseEntity<?> createNewCategory(@RequestBody NewCategoryDTO categories){
		try {
			categories.setCreatedAt(new Date(System.currentTimeMillis()));
			newCategoryRepo.save(categories);
			return new ResponseEntity<NewCategoryDTO>(categories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Get category by id
	@GetMapping("/new-categories/{id}")
	public ResponseEntity<?> getANewCatEntity(@PathVariable("id") String id){
		Optional<NewCategoryDTO> newCategoryOptional = newCategoryRepo.findById(id);
		if(newCategoryOptional.isPresent()) {
			return new ResponseEntity<>(newCategoryOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("New Category Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	//Update category by id
	@PutMapping("new-categories/{id}")
	public ResponseEntity<?> updateNewCategory(@PathVariable("id") String id, @RequestBody NewCategoryDTO categories){
		Optional<NewCategoryDTO> newCategoryOptional = newCategoryRepo.findById(id);
		if(newCategoryOptional.isPresent()) {
			NewCategoryDTO newCategoryToSave = newCategoryOptional.get();
			newCategoryToSave.setTitle(categories.getTitle() != null ? categories.getTitle() : newCategoryToSave.getTitle());
			newCategoryToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
			newCategoryRepo.save(newCategoryToSave);
			return new ResponseEntity<>(newCategoryToSave, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Category not found with id "+id, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("new-categories/{id}")
	public ResponseEntity<?> deleteNewCategory(@PathVariable("id") String id){
		try {
			newCategoryRepo.deleteById(id);
			return new ResponseEntity<>("Category deleted!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
