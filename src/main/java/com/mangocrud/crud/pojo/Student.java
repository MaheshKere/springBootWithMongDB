package com.mangocrud.crud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Set;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    private String id;

    private String name;

    private String std;

    @DocumentReference
   // @DBRef
    private Set<Book> bookList;

}
