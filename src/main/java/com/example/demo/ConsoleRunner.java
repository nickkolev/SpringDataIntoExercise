package com.example.demo;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAuthors();
        //this.seedService.seedAll();

        //query01_booksAfter2000();
        //query02_allAuthorsWithBooksBefore1990();
        //query03_allAuthorsOrderedByBookCount();
        query04_allBooksFromAuthorDescReleaseDateAscBookTitle();
    }

    private void query01_booksAfter2000() {
        LocalDate year2000 = LocalDate.of(1999,12,31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);
        int booksAfterYearCount = this.bookRepository.countByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));
        System.out.println(booksAfterYearCount);
    }

    private void query02_allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990,1,1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    public void query03_allAuthorsOrderedByBookCount() {

        List<Author> authors = this.authorRepository.findAll();

        authors.stream()
                .sorted(Comparator.comparingInt(a -> a.getBooks().size()))
                .forEach(a -> System.out.printf("%s %s -> %d%n",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBooks().size()));
    }

    public void query04_allBooksFromAuthorDescReleaseDateAscBookTitle() {
        Author author = authorRepository.findByFirstNameAndLastName("George", "Powell");
        List<Book> allByAuthor = bookRepository.findAllByAuthor(author);

        allByAuthor.stream()
                .sorted((o1, o2) -> {
                    int sortedResult = o2.getReleaseDate().compareTo(o1.getReleaseDate());
                    return sortedResult == 0 ? o1.getTitle().compareTo(o2.getTitle()) : sortedResult;
                })
                .forEach(e -> System.out.printf("%s - %s - %d copies%n", e.getTitle(), e.getReleaseDate(), e.getCopies()));
    }
}
