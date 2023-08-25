package com.javatechie.spring.mongo.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "newCategories")
public class NewCategoryDTO {
	@Id
	private String idString;
	
	private String title;
	
	private Date createdAt;
	
	private Date updatedAt;
}

