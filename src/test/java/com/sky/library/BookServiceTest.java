package com.sky.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BookServiceTest {

	private BookService bookService;
    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";

	@BeforeEach
	public void setUp() {
		bookService = new BookServiceImpl(new BookRepositoryStub());
	}

	@Test
	@DisplayName("Should return an exception .... if the reference is does not begin with BOOK")
	public void retrieveBookFirstCase() {
		String bookReference = "INVALID-TEXT";

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bookService.retrieveBook(bookReference);
        });

        assertEquals(thrown.getMessage(), "book reference must begin with BOOK-");

	}

    @Test
    @DisplayName("Should return an BookNotFoundExecption exception if the reference is does not exist")
    public void retrieveBookSecondCase() {
        String bookReference = "BOOK-999";

        assertThrows(BookNotFoundException.class, () -> {
            bookService.retrieveBook(bookReference);
        });
    }

	@Test
    @DisplayName("Should return The Gruffalo book from the repository with the reference BOOK-GRUFF472")
	public void retrieveBookThirdCase() throws BookNotFoundException {
	    String bookReference = "BOOK-GRUFF472";

		Book book = bookService.retrieveBook(bookReference);
        assertNotNull(book);
        assertEquals(book.getReference(), bookReference);
        assertEquals(book.getTitle(), "The Gruffalo");

	}

    @Test
    @DisplayName("Should return an exception .... if the reference is does not begin with BOOK")
    public void getBookSummaryFirstCase() {
        String bookReference = "INVALID-TEXT";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bookService.getBookSummary(bookReference);
        });

        assertEquals(thrown.getMessage(), "book reference must begin with BOOK-");

    }

    @Test
    @DisplayName("Should return an BookNotFoundExecption exception if the reference is does not exist")
    public void getBookSummarySecondCase() {
        String bookReference = "BOOK-999";

        assertThrows(BookNotFoundException.class, () -> {
            bookService.getBookSummary(bookReference);
        });
    }

    @Test
    @DisplayName("Should return The Gruffalo summary from the repository with the reference BOOK-GRUFF472")
    public void getBookSummaryThirdCase() throws BookNotFoundException {
        String bookReference = "BOOK-GRUFF472";

        String summary = bookService.getBookSummary(bookReference);
        assertEquals(summary, "[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods");

    }

    @Test
    @DisplayName("Should return The Wind In The Willows summary from the repository with the reference BOOK-WILL987")
    public void getBookSummaryFourthCase() throws BookNotFoundException {
        String bookReference = "BOOK-WILL987";

        String summary = bookService.getBookSummary(bookReference);
        assertEquals(summary, "[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...");

    }
}