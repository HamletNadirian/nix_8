package ua.com.alevel.db.impl;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.db.AuthorDB;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.util.DBHelperUtil;

import java.util.Objects;
import java.util.UUID;

public class AuthorDBImpl implements AuthorDB {
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorDBImpl authorDB = (AuthorDBImpl) o;
		return Objects.equals(authors, authorDB.authors) && Objects.equals(books, authorDB.books);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, books);
	}

	private ArrayListMy<Author> authors;
	private ArrayListMy<Book> books;

	public AuthorDBImpl() {
		authors = new ArrayListMy<>();
		books = new ArrayListMy<>();
	}

	@Override
	public boolean existByAuthor(String name) {
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i) == null) {
				return false;
			}
			if (authors.get(i).equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayListMy<Book> findAllBooks(Author author) {
		return author.getBookArrayListMy();
	}

	@Override
	public void create(Author author) {
		if (StringUtils.isNotBlank(author.getName())) {
			author.setId(generateId());
			authors.add(author);
		} else throw new RuntimeException("Name is not present");
	}

	@Override
	public void update(Author author) {
		Author current = findById(author.getId());
		if (current != null) {
			current.setName(author.getName());
		} else System.out.println("Your input incorrect id");
	}

	@Override
	public void delete(String id) {
		if (StringUtils.isNotBlank(id)) {
			for (int i = 0; i < authors.size(); i++) {
				if (authors.get(i).getId().equals(id)) {
					authors.remove(i);
				}
			}
		} else
			throw new RuntimeException("author not found by id " + id);
	}

	@Override
	public Author findById(String id) {
		int tempId = searchId(id);
		if (tempId == -1) {
			System.out.println("Not found");
			throw new RuntimeException("email is not present");
			//	return null;
		}
		return authors.get(tempId);
	}

	@Override
	public Author findAuthor(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (int i = 0; i < authors.size(); i++) {
				if (authors.get(i).getName().equals(name)) {
					return authors.get(i);
				}
			}
		}
		throw new RuntimeException("email is not present");

//		return null;
	}

	public int searchId(String id) {
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getId().equals(id)) {
				return (i);
			}
		}
		throw new RuntimeException("email is not present");


		//	return -1;
	}

	@Override
	public ArrayListMy<Author> findAll() {
		return authors;
	}

	private String generateId() {
		String id = UUID.randomUUID().toString();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId().equals(id)) {
				return generateId();
			}
		}
		return id;
	}
}
