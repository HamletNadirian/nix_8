package ua.com.alevel.db;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.db.BaseDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface AuthorDB extends BaseDB<Author> {
	boolean existByAuthor(String name);

	ArrayListMy<Book> findAllBooks(Author author);

	Author findAuthor(String name);
}
