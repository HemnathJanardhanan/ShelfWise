package com.shelfwise.backend.modules.books.service;

import com.shelfwise.backend.modules.author.Author;
import com.shelfwise.backend.modules.author.AuthorRepository;
import com.shelfwise.backend.modules.books.models.Book;
import com.shelfwise.backend.modules.books.repository.BookCopyRepository;
import com.shelfwise.backend.modules.books.repository.BookRepository;
import com.shelfwise.backend.modules.books.utils.dto.BookDto;
import com.shelfwise.backend.modules.books.utils.dto.NewBookRequest;
import com.shelfwise.backend.modules.books.utils.mapper.BookMapper;
import com.shelfwise.backend.modules.publisher.Publisher;
import com.shelfwise.backend.modules.publisher.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {

    BookRepository bookrepo;
    BookMapper mapper;
    AuthorRepository authorRepo;
    PublisherRepository publisherRepo;
    BookCopyRepository  copyRepo;

    public BookService(BookRepository repo, BookMapper mapper,AuthorRepository authorRepo,PublisherRepository publisherRepo,BookCopyRepository  copyRepo) {
        this.bookrepo = repo;
        this.mapper = mapper;
        this.authorRepo = authorRepo;
        this.publisherRepo = publisherRepo;
        this.copyRepo = copyRepo;
    }

    public List<Author> validateAuthor(String[] req){
        List<Author> authors=new ArrayList<>();
        for(String author:req){
            Author authorObj = authorRepo.findByAuthorName(author)
                    .orElseGet(() -> authorRepo.save(Author.builder().authorName(author).build()));
            authors.add(authorObj);
        }
        return authors;
    }
    public Publisher validatePublisher(String req){

        Publisher publisherObj = publisherRepo.findByPublisherName(req)
                .orElseGet(() -> publisherRepo.save(Publisher.builder().publisherName(req).build()));

        return  publisherObj;
    }

    public BookDto addBook(NewBookRequest req) {
        String[] input=req.getAuthors().split(",");
        List<Author> authors=validateAuthor(input);
        Publisher publishers=validatePublisher(req.getPublisher());

        Book newBook = Book.builder()
                .title(req.getTitle())
                .authors(authors)
                .publisher(publishers)
                .publishDate(req.getPublishDate())
                .genre(req.getGenre())
                .isbn(req.getIsbn())

                .build();
        
        Book saved = bookrepo.save(newBook);
        return mapper.toBookDto(saved);
    }

    public List<BookDto> getAllBooks(){
        List<Book> books=bookrepo.findAll();
        List<BookDto> bookDtos=new ArrayList<>();
        for(Book book:books){
            bookDtos.add(mapper.toBookDto(book));
        }
        return bookDtos;
    }

    public BookDto getBookById(long id) {
        Book book=bookrepo.findByBookId(id).orElseThrow(()-> new EntityNotFoundException("Book not found with id "+id));
        return mapper.toBookDto(book);
    }

    public BookDto updateBook(long bookId,NewBookRequest req) {

        Book toUpdateBook=bookrepo.findByBookId(bookId).orElseThrow(()-> new EntityNotFoundException("Book not found with id " + bookId));
        List<Author> authors=validateAuthor(req.getAuthors().split(","));
        Publisher publishers=validatePublisher(req.getPublisher());

        toUpdateBook.setTitle(req.getTitle());
        toUpdateBook.setAuthors(authors);
        toUpdateBook.setPublisher(publishers);
        toUpdateBook.setGenre(req.getGenre());
        toUpdateBook.setPublishDate(req.getPublishDate());
        toUpdateBook.setIsbn(req.getIsbn());


        bookrepo.save(toUpdateBook);
        return mapper.toBookDto(toUpdateBook);
    }

    public void deleteBook(long bookId) {
        if(bookrepo.existsById(bookId)){
            bookrepo.deleteById(bookId);
        }else{
            throw new EntityNotFoundException("Book not found with id "+bookId);
        }
    }
}
