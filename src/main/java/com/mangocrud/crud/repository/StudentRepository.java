package com.mangocrud.crud.repository;

import com.mangocrud.crud.pojo.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
