package com.example.demostudent2025.persistence.repository;

import com.example.demostudent2025.persistence.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<BookEntity, String> {


    Optional<BookEntity> findById(String id);

    Optional<BookEntity> findByTitle(String title);

    boolean existsByTitle(String title);

    boolean existsByAuthor(String author);

    boolean existsByPublisher(String publisher);

    boolean existsByYear(String year);


}
