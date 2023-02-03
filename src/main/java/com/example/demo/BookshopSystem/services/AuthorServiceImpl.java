package com.example.demo.BookshopSystem.services;

import com.example.demo.BookshopSystem.entities.Author;
import com.example.demo.BookshopSystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long authorsCount = authorRepository.count();

        Random random = new Random();

        int authorId = random.nextInt((int) authorsCount) + 1;

        return authorRepository.getById(authorId);
    }
}
