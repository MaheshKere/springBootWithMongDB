package com.mangocrud.crud.restcontroller;

import com.mangocrud.crud.pojo.Book;
import com.mangocrud.crud.pojo.Student;
import com.mangocrud.crud.pojo.User;
import com.mangocrud.crud.repository.BookRepository;
import com.mangocrud.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class BookRestController {
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public MongoTemplate mongoTemplate;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/addBook")
    public Book addUser(@RequestBody Book book) {

        return bookRepository.save(book);
    }

    @DeleteMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable String id){

        for (Student student : studentRepository.findAll()) {
            for (Book book : student.getBookList()) {
               if (book.getBookId().equals(id)){
                   return "Book Can not be deleted";
               }
            }
        }

        bookRepository.deleteById(id);
        return "Book Deleted";

    }

    @GetMapping("/getbooks")
    public List<Book> fetchStudent(){
        return bookRepository.findAll();
    }
    public void checkBookAndUpdateCount(Set<Book> bookList) throws Exception {
        for (Book book : bookList) {
            Optional<Book> bookObj =bookRepository.findById(book.getBookId());
            if (bookObj.isPresent()) {
                Book book1 =bookObj.get();
                if(book1.getTotal_no_of_copies()-1 > 0) {
                    Query query = new Query().addCriteria(Criteria.where("_id").is(book1.getBookId()));
                    Update update =new Update();
                    update.set("total_no_of_copies",book1.getTotal_no_of_copies() - 1);
                    update.set("booked_copies",book1.getBooked_copies()+1);
                    mongoTemplate.updateFirst(query,update,Book.class);
                }else{
                    throw new Exception("Out of Stock..............");
                }
            }else{
                throw new Exception("Book not found................");
            }
        }

    }
}
