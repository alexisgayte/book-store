# Book Store

Interview exercise

## Resume

Book Store interaction.

 - Retrieve a book
 - Get book summary

### Requirement : 


Complete the implementation of the BookService interface. The first part of the problem is to complete the following method:
     retrieveBook(String bookReference) throws BookNotFoundException
Unsurprisingly, it should retrieve a book by its reference. However, the method must also • Ensure that the bookReference is prefixed by ‘BOOK-‘
- Throw a BookNotFoundException where appropriate For the second method;
     getBookSummary(String bookReference) throws BookNotFoundException
This method must
- Ensure that the bookReference is prefixed by ‘BOOK-‘
- Throw a BookNotFoundException where appropriate
- Provide a book summary that concatenates the reference, the title, and the first nine
words of the review. If the review is longer than nine words put an ellipsis (‘...’) at the end

## Building the application

The project uses [Maven](https://maven.apache.org) as a build tool.

### Running the test

To run the test execute the following command:

```bash
  ./mvn test
```



