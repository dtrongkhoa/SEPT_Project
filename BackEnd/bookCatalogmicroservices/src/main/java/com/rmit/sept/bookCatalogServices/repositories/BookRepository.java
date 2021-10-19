package com.rmit.sept.bookCatalogServices.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rmit.sept.bookCatalogServices.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@SuppressWarnings("SqlResolve")
@Repository
public class BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM BOOK", new bookRowMapper());
    }

    public Book getByISBN(String ISBN) {
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ISBN = ?", new Object[] {ISBN}, new bookRowMapper());
    }
//    public List<Book> getByTitle(String title) {
//        return jdbcTemplate.query("SELECT * FROM BOOK WHERE LOWER(title) LIKE ?", new Object[] {title.toLowerCase()}, new bookRowMapper());
//    }

//    public List<Book> getByAuthor(String author) {
//        String keyword = '%' + author + '%';
//        return jdbcTemplate.query("SELECT * FROM BOOK WHERE LOWER(AUTHORFIRST) LIKE ? OR LOWER(AUTHORLAST) LIKE ?", new Object[] {keyword.toLowerCase(), keyword.toLowerCase()}, new bookRowMapper());
//    }

    public List<Book> getByAll(String word) {
        String keyword = '%' + word + '%';
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE LOWER(TITLE) LIKE ? OR LOWER(AUTHORFIRST) LIKE ? OR LOWER(AUTHORLAST) LIKE ? OR LOWER(PUBLISHER) LIKE ? OR LOWER(PUBLISHEDYEAR) LIKE ? OR LOWER(ISBN) LIKE ?", new Object[] {keyword.toLowerCase(), keyword.toLowerCase(), keyword.toLowerCase(), keyword.toLowerCase(), keyword.toLowerCase(), keyword.toLowerCase()}, new bookRowMapper());
    }

    public List<String> getCategoriesForBookByTitle(String title) {
        return jdbcTemplate.queryForList("SELECT CATEGORYTYPE FROM CATEGORY AS C JOIN BOOKCATEGORY AS BC ON C.CATEGORYID = BC.CATEGORYID JOIN BOOK AS B ON B.ISBN = BC.ISBN WHERE B.TITLE = ?", new Object[] {title}, String.class);
    }

    public List<Book> getTrendingBooks() {
        int[] index = new int[] {3, 55, 52, 25, 47, 27, 77, 32, 24, 36, 79, 70, 37, 85, 61, 15, 63, 90, 22, 19};
        List<Book> results = new ArrayList<>();
        for (int j : index) {
            Book book = jdbcTemplate.queryForObject("SELECT * FROM BOOK LIMIT 1 OFFSET ?", new Object[]{j - 1}, new bookRowMapper());
            results.add(book);
        }
        return results;
    }

}

class bookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int row) throws SQLException {
        Book book = new Book();
        book.setTitle(rs.getString("title"));
        book.setAuthorFirst(rs.getString("authorFirst"));
        book.setAuthorLast(rs.getString("authorLast"));
        book.setDescription(rs.getString("description"));
        book.setCoverImage(rs.getString("coverImage"));
        book.setPages(rs.getString("pages"));
        book.setPublisher(rs.getString("publisher"));
        book.setPublishedYear(rs.getString("publishedYear"));
        book.setIsbn(rs.getString("ISBN"));
        return book;
    }

}