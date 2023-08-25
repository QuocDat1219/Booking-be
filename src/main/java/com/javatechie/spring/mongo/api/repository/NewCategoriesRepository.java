package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.javatechie.spring.mongo.api.model.NewCategoryDTO;

@Repository
public interface NewCategoriesRepository extends MongoRepository<NewCategoryDTO, String> {

}
