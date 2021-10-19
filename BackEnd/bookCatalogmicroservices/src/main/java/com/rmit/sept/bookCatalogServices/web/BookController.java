package com.rmit.sept.bookCatalogServices.web;

import java.util.List;

import com.rmit.sept.bookCatalogServices.model.Book;
import com.rmit.sept.bookCatalogServices.repositories.BookRepository;

import com.rmit.sept.bookCatalogServices.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{ISBN}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable String ISBN) {
        Book book = bookService.getBookByISBN(ISBN);
        if (book == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/searchResults/{searchTerm}")
    public ResponseEntity<List<Book>> getSearchResult(@PathVariable String searchTerm) {
        List<Book> results = bookService.getSearchResults(searchTerm);
        if (results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/trending")
    public List<Book> getTrendingBooks() {
        return bookService.getTrendingBooks();
    }

    @GetMapping("/categories/{title}")
    public List<String> getCategoriesForBookByTitle(@PathVariable String title) { return bookService.getCategoriesForBook(title); }

}
