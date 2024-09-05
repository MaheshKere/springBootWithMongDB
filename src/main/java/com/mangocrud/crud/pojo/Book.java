package com.mangocrud.crud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    private String bookId;

    @Indexed(unique = true, name="book_name_index")
    private String bookName;

    private Integer total_no_of_copies;

    private Integer booked_copies;

    public Book(String bookId){
        this.bookId = bookId;
    }
}
