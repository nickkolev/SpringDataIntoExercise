package com.example.demo.BookshopSystem.repositories;

import com.example.demo.BookshopSystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

    Author findByFirstNameAndLastName(String firstName, String lastName);

}