package ua.com.alevel.entity;

import java.util.Objects;

public class Book extends BaseEntity {

	private String name;
	private int price;
	Author author;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return price == book.price && Objects.equals(name, book.name) && Objects.equals(author, book.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price, author);
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return " Book{" +
				"id='" + getAuthor() + '\'' +
				"id='" + super.getId() + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}


}
