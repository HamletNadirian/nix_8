package ua.com.alevel.entity;

import ua.com.alevel.arraylist.ArrayListMy;

public class Author extends BaseEntity {
	String name;
	Book books;
	ArrayListMy<Book> bookArrayListMy;

	public Author() {
		bookArrayListMy = new ArrayListMy<>();
	}

	public ArrayListMy<Book> getBookArrayListMy() {
		return bookArrayListMy;
	}

	public void setBookArrayListMy(ArrayListMy<Book> bookArrayListMy) {
		this.bookArrayListMy = bookArrayListMy;
	}

	public Book getBooks() {
		return books;
	}

	public void setBooks(Book books) {
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id{" + super.getId() + '\'' +
				"name='" + name + '\'' +
				'}';
	}
}
