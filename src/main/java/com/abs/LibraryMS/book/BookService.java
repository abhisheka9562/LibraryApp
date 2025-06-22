package com.abs.LibraryMS.book;

import java.util.List;

public interface BookService
{
    boolean addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    boolean updateBookById(Long id,Book updatedBook);
    boolean deleteBookById(Long id);
}
