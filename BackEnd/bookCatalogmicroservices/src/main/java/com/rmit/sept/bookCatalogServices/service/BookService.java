package com.rmit.sept.bookCatalogServices.service;

import com.rmit.sept.bookCatalogServices.model.Book;
import com.rmit.sept.bookCatalogServices.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookByISBN (String ISBN) {
        return bookRepository.getByISBN(ISBN);
    }

    public List<Book> getSearchResults (String searchTerm) {
        return bookRepository.getByAll(searchTerm);
    }

    public List<Book> getTrendingBooks() {
        return bookRepository.getTrendingBooks();
    }

    public List<String> getCategoriesForBook (String title) {
        return bookRepository.getCategoriesForBookByTitle(title);
    }

}
