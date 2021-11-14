package ua.com.alevel.service.impl;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorServiceImpl implements AuthorService {

	private final AuthorDao authorDao = ObjectFactory.getCurrentObject(AuthorDao.class);

	@Override
	public boolean existByAuthor(String author) {
		return authorDao.existByAuthor(author);
	}

	@Override
	public Author findAuthor(String author) {
		return authorDao.findByAuthor(author);
	}

	@Override
	public ArrayListMy<Book> findAllBooks(Author author) {
		return authorDao.findAllBooks(author);
	}

	@Override
	public void create(Author author) {
		authorDao.create(author);
	}

	@Override
	public void update(Author author) {
		authorDao.update(author);
	}

	@Override
	public void delete(String id) {
		authorDao.delete(id);
	}

	@Override
	public Author findById(String id) {
		return authorDao.findById(id);
	}

	@Override
	public ArrayListMy<Author> findAll() {
		return authorDao.findAll();
	}
}
