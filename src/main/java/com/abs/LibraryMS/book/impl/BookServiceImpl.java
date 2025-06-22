package com.abs.LibraryMS.book.impl;

import com.abs.LibraryMS.book.Book;
import com.abs.LibraryMS.book.BookRepository;
import com.abs.LibraryMS.book.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public boolean addBook(Book book) {
        bookRepository.save(book);
        return true;
    }

    @Override
    public List<Book> getAllBooks()
    {
      return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Book book=bookRepository.findById(id).orElse(null);
        return book;
    }

    @Override
    public boolean updateBookById(Long id,Book updatedBook) {
        Book existingBook=bookRepository.findById(id).orElse(null);
        if(existingBook==null)
        {
            return false;
        }
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setPublished_year(updatedBook.getPublished_year());
        bookRepository.save(existingBook);
        return true;
    }

    @Override
    public boolean deleteBookById(Long id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
