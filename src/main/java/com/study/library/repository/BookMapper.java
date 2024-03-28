package com.study.library.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.study.library.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    public int saveBook(Book book);

    public List<Book> findBooks(
            @Param("startIndex") int page,
            @Param("count") int count,
            @Param("bookTypeId") int bookTypeId,
            @Param("categoryId") int categoryId,
            @Param("searchTypeId") int searchTypeId,
            @Param("searchText") String searchText);

    public int getBookCount(
            @Param("bookTypeId") int bookTypeId,
            @Param("categoryId") int categoryId,
            @Param("searchTypeId") int searchTypeId,
            @Param("searchText") String searchText );

    public int deleteBooksByBookIds(List<Integer> bookIds);

    public int updateBookByBookId(Book book);

}

