package com.mangocrud.crud.restcontroller;

import com.mangocrud.crud.pojo.Book;
import com.mangocrud.crud.pojo.Student;
import com.mangocrud.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * StudentRestController
 */
@RestController
public class Studentcontroller {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRestController bookRestController ;

    @Autowired
    public MongoTemplate mongoTemplate;

    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student) throws Exception {
        bookRestController.checkBookAndUpdateCount(student.getBookList());
     return  studentRepository.save(student);
    }


    @GetMapping("/getstudents")
    public List<Student> fetchStudent(){
        return studentRepository.findAll();
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student) throws Exception {
        Optional<Student> bookObj =studentRepository.findById(student.getId());
        if(bookObj.isPresent()){
            bookRestController.checkBookAndUpdateCount(student.getBookList());
            Query query = new Query().addCriteria(Criteria.where("_id").is(student.getId()));
            Update update =new Update();
            bookObj.get().getBookList().addAll(student.getBookList());
            update.set("bookList",bookObj.get().getBookList());
            mongoTemplate.updateFirst(query,update, Student.class);
        }else{
            return "Student Not Found";
        }
        return "Student Info Updated ";
    }
}
