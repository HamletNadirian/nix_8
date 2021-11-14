package ua.com.alevel.dao.impl;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.db.BookDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class BookDaoImpl implements BookDao {
	private final BookDB db = ObjectFactory.getInstance().getCurrentObject(BookDB.class);

	@Override
	public void create(Book book) {
		db.create(book);
	}

	@Override
	public void update(Book book) {
		db.update(book);
	}

	@Override
	public void delete(String id) {
		db.delete(id);
	}

	@Override
	public Book findById(String id) {
		return db.findById(id);
	}

	@Override
	public ArrayListMy<Book> findAll() {
		return db.findAll();
	}

	@Override
	public Book findBook(String id) {
		return db.findBook(id);
	}
}

