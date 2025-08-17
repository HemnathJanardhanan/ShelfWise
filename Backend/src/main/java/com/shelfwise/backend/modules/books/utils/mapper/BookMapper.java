package com.shelfwise.backend.modules.books.utils.mapper;
import com.shelfwise.backend.modules.author.Author;
import com.shelfwise.backend.modules.books.models.Book;
import com.shelfwise.backend.modules.books.utils.dto.BookDto;
import com.shelfwise.backend.modules.publisher.Publisher;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class BookMapper {
    public List<String> authorToString(List<Author> authors){
        List<String> authorString=new ArrayList<>();
        for(Author author:authors){
            authorString.add(author.getAuthorName());
        }
        return authorString;
    }
    public String publisherToString(Publisher publisher){
        return publisher.getPublisherName();
    }
    public BookDto toBookDto(Book book){
        BookDto dto=new BookDto();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setAuthorNames(authorToString(book.getAuthors()));
        dto.setPublisherName(publisherToString(book.getPublisher()));
        dto.setPublishDate(book.getPublishDate());
        dto.setGenre(book.getGenre());
        dto.setIsbn(book.getIsbn());

        return dto;

    }


}
