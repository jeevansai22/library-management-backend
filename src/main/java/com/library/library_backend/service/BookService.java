
package com.library.library_backend.service;

import com.library.library_backend.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(3);

    public BookService() {
        books.add(new Book(1L, "The Alchemist",
                "Paulo Coelho", "Fiction", true));
        books.add(new Book(2L, "Wings of Fire",
                "A. P. J. Abdul Kalam", "Biography", true));
        books.add(new Book(3L, "Clean Code",
                "Robert C. Martin", "Programming", false));
    }

    public synchronized List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public synchronized Book addBook(Book book) {
        book.setId(counter.incrementAndGet());
        book.setAvailable(true);
        books.add(book);
        return book;
    }

    public synchronized boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}
