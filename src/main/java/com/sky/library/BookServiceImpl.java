package com.sky.library;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final int WORDS_BOUND = 9;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        if (!bookReference.startsWith(BOOK_REFERENCE_PREFIX)) {
            throw new IllegalArgumentException("book reference must begin with BOOK-");
        }

        Book book = bookRepository.retrieveBook(bookReference);
        if (book == null) {
            throw new BookNotFoundException();
        }

        return book;
    }

    public String getBookSummary(String bookReference) throws BookNotFoundException {
        if (!bookReference.startsWith(BOOK_REFERENCE_PREFIX)) {
            throw new IllegalArgumentException("book reference must begin with BOOK-");
        }

        Book book = bookRepository.retrieveBook(bookReference);
        if (book == null) {
            throw new BookNotFoundException();
        }

        String[] parts = book.getReview().split("(\\s|,\\s)");
        boolean ellipsis = parts.length > WORDS_BOUND;

        String words = Arrays.asList(parts).stream().limit(WORDS_BOUND).collect(Collectors.joining(" "));

        String endLine = ellipsis ? "..." : "";
        return String.format("[%s] %s - %s%s", book.getReference(), book.getTitle(), words, endLine);
    }

}
