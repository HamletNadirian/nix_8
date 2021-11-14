package ua.com.alevel.dao;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;

public interface AuthorDao extends BaseDao <Author> {
	boolean existByAuthor(String author);
	Author findByAuthor(String name);
	ArrayListMy<Book>findAllBooks(Author author);

}
