package com.abs.LibraryMS.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> bookList=bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id)
    {
        Book book=bookService.getBookById(id);
        if(book==null)
        {
            return new ResponseEntity<>("Book with id:"+id+" not found.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book)
    {
        boolean saved= bookService.addBook(book);
        if(saved)
        {
            return new ResponseEntity<>("Book saved successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Book not saved",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBookById(@PathVariable Long id,@RequestBody Book book)
    {
        boolean updated= bookService.updateBookById(id,book);
        if(updated)
        {
            return new ResponseEntity<>("Book updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Book with id: "+id+" not found.",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id)
    {
        boolean deleted= bookService.deleteBookById(id);
        if(deleted)
        {
            return new ResponseEntity<>("Book deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Book with id: "+id+" not found.",HttpStatus.NOT_FOUND);
    }

}
