package ua.com.alevel.service.impl;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BaseService;
import ua.com.alevel.service.BookService;

public class BookServiceImpl implements BookService {
	private final BookDao bookDao = ObjectFactory.getInstance().getCurrentObject(BookDao.class);

	@Override
	public void create(Book book) {
		bookDao.create(book);
	}

	@Override
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	public void delete(String id) {
		bookDao.delete(id);
	}

	@Override
	public Book findById(String id) {
		return bookDao.findById(id);
	}

	@Override
	public ArrayListMy<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book findAuthor(String name) {
		return bookDao.findBook(name);
	}
}
