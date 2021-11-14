package ua.com.alevel.dao.impl;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public class AuthorDaoImpl implements AuthorDao {
	private final AuthorDB db = ObjectFactory.getCurrentObject(AuthorDB.class);
	public static AuthorDaoImpl instance;

	@Override
	public boolean existByAuthor(String author) {
		return db.existByAuthor(author);
	}

	@Override
	public Author findByAuthor(String name) {
		return db.findAuthor(name);
	}

	public static AuthorDaoImpl getInstance() {
		if (instance == null) {
			instance = new AuthorDaoImpl();
		}
		return instance;
	}

	@Override
	public ArrayListMy<Book> findAllBooks(Author author) {
		return db.findAllBooks(author);
	}


	@Override
	public void create(Author author) {
		db.create(author);
	}

	@Override
	public void update(Author author) {
		db.update(author);
	}

	@Override
	public void delete(String id) {
		db.delete(id);
	}

	@Override
	public Author findById(String id) {
		return db.findById(id);
	}

	@Override
	public ArrayListMy<Author> findAll() {
		return db.findAll();
	}
}
