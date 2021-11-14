package ua.com.alevel.service;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface AuthorService extends BaseService<Author> {
	boolean existByAuthor(String author);
	Author findAuthor(String author);
	ArrayListMy<Book> findAllBooks(Author author);

}
