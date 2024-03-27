package com.study.library.service;

import com.study.library.dto.RegisterBookReqDto;
import com.study.library.dto.SearchBookCountRespDto;
import com.study.library.dto.SearchBookReqDto;
import com.study.library.dto.SearchBookRespDto;
import com.study.library.entity.Book;
import com.study.library.repository.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveBook(RegisterBookReqDto registerBookReqDto) {
        bookMapper.saveBook(registerBookReqDto.toEntity());
    }

    public List<SearchBookRespDto> searchBooks(SearchBookReqDto searchBookReqDto) {
        int startIndex = (searchBookReqDto.getPage() - 1) * searchBookReqDto.getCount();

        List<Book> books = bookMapper.findBooks(
                startIndex,
                searchBookReqDto.getCount(),
                searchBookReqDto.getBookTypeId(),
                searchBookReqDto.getCategoryId(),
                searchBookReqDto.getSearchTypeId(),
                searchBookReqDto.getSearchText()
        );

        return books.stream().map(Book::toSearchBookRespDto).collect(Collectors.toList());
    }

    public SearchBookCountRespDto getBookCount(SearchBookReqDto searchBookReqDto) {
        int bookCount = bookMapper.getBookCount(
                searchBookReqDto.getBookTypeId(),
                searchBookReqDto.getCategoryId(),
                searchBookReqDto.getSearchTypeId(),
                searchBookReqDto.getSearchText()

        );

        int maxPageNumber = (int) Math.ceil(((double) bookCount) / searchBookReqDto.getCount());

        return SearchBookCountRespDto.builder()
                .totalCount(bookCount)
                .maxPageNumber(maxPageNumber)
                .build();

    }
}
