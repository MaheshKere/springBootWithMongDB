package com.mangocrud.crud.repository;

import com.mangocrud.crud.pojo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
