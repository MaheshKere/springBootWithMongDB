package com.mangocrud.crud.repository;

import com.mangocrud.crud.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}