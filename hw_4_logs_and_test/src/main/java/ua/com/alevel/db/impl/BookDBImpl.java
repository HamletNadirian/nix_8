package ua.com.alevel.db.impl;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.dao.impl.BookDaoImpl;
import ua.com.alevel.db.BaseDB;
import ua.com.alevel.db.BookDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;
import ua.com.alevel.util.DBHelperUtil;

public class BookDBImpl implements BookDB {
	private ArrayListMy<Book> books;

	public BookDBImpl() {
		books = new ArrayListMy<>();
	}

	@Override
	public void create(Book book) {
		if (StringUtils.isNotBlank(book.getName())) {
			book.setId(DBHelperUtil.generateId(books));
			books.add(book);
		} else throw new RuntimeException("Name is not present");

	}

	@Override
	public void update(Book book) {
		try {
			Book current = findById(book.getId());
			current.setName(book.getName());
		} catch (NullPointerException e) {
			System.out.println("Your id incorrect");
		}

	}

	@Override
	public void delete(String id) {
		if (StringUtils.isNotBlank(id)) {

			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getId().equals(id)) {
					books.remove(i);
				}
			}
		} else
			throw new RuntimeException("books not found by id " + id);
	}

	@Override
	public Book findById(String id) {
		int tempId = searchId(id);
		if (tempId == -1) {
			//System.out.println("Not found");
			throw new RuntimeException("id is not present");

		}
		return books.get(tempId);
	}

	public int searchId(String id) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId().equals(id)) {
				return (i);
			}
		}
		throw new RuntimeException("id is not present");
	}

	@Override
	public ArrayListMy<Book> findAll() {
		return books;
	}

	@Override
	public Book findBook(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getName().equals(name)) {
					return books.get(i);
				}
			}
		}
		throw new RuntimeException("book not found by id " + name);
	}
}

