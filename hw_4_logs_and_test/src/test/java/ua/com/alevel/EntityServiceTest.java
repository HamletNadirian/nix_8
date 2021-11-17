package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;
import ua.com.alevel.view.AuthorController;

import static ua.com.alevel.EntityTestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntityServiceTest {

	private final Author author = new Author();
	static final AuthorService authorService = ObjectFactory.getCurrentObject(AuthorService.class);

	private final Book book = new Book();
	static final BookService bookService = ObjectFactory.getCurrentObject(BookService.class);

	@Test
	@Order(1)
	public void shouldDoCreateAuthorAndBookWhenNameIsNotEmpty() {
		author.setName(AUTHOR_NAME);
		authorService.create(author);
		book.setPrice(PRICE);
		book.setName(BOOK_NAME);
		bookService.create(book);

		Assertions.assertNotNull(author);
		Assertions.assertEquals(author.getName(), AUTHOR_NAME);
		Assertions.assertNotNull(book);
		Assertions.assertEquals(book.getPrice(), PRICE);

	}

	@Test
	@Order(2)
	public void shouldDoCreateBookAndAuthorWhenNameIsEmpty() {
		book.setName(null);
		book.setPrice(PRICE);
		author.setName(null);

		Assertions.assertThrows(
				RuntimeException.class,
				() -> bookService.create(book),
				"Name book is not present");

		Assertions.assertThrows(
				RuntimeException.class,
				() -> authorService.create(author),
				"Name author is not present");
	}

	@Test
	@Order(3)
	public void shouldDoReturnAuthorAndBookDtoWhenNameIsEmpty() {
		Assertions.assertThrows(
				RuntimeException.class,
				() -> authorService.findAuthor(null),
				"author is not present");
		Assertions.assertThrows(
				RuntimeException.class,
				() -> bookService.findAuthor(null),
				"book is not present");
	}

	@Test
	@Order(4)
	public void shouldDoReturnListEntityDtoWhenResponseIsNotEmpty() {
		ArrayListMy<Book> bookArrayListMy = bookService.findAll();
		ArrayListMy<Author> authorArrayListMy = authorService.findAll();

		Assertions.assertEquals(bookArrayListMy.size(), 1);
		Assertions.assertEquals(authorArrayListMy.size(), 1);
	}

	@Test
	@Order(5)
	public void shouldDoUpdateEntityDoesNotThrow() {
		ArrayListMy<Author> entitiesAuthor = authorService.findAll();
		Author author = entitiesAuthor.get(0);

		Author entityAuthor = new Author();
		entityAuthor.setId(author.getId());
		entityAuthor.setName(AUTHOR_NAME);

		Assertions.assertDoesNotThrow(() -> authorService.update(entityAuthor));

		ArrayListMy<Book> books = bookService.findAll();
		Book dto = books.get(0);

		Book book = new Book();
		book.setId(dto.getId());
		book.setName(BOOK_NAME);

		Assertions.assertDoesNotThrow(() -> bookService.update(book));
	}

	@Test
	@Order(6)
	public void shouldDoUpdateEntityDoThrow() {
		Author author = new Author();
		author.setId(null);
		author.setName(AUTHOR_NAME);

		Assertions.assertThrows(
				RuntimeException.class,
				() -> authorService.update(author),
				"author not found by id " + null);

		Book book = new Book();
		book.setId(null);
		book.setName(AUTHOR_NAME);

		Assertions.assertThrows(
				RuntimeException.class,
				() -> bookService.update(book),
				"book not found by id " + null);
	}

	@Test
	@Order(7)
	public void shouldDoReturnEntityDtoWhenIdIsNotEmpty() {
		ArrayListMy<Author> authors = authorService.findAll();
		Author author = authors.get(0);
		Assertions.assertNotNull(author);
		Assertions.assertEquals(author.getName(), AUTHOR_NAME);

		ArrayListMy<Book> books = bookService.findAll();
		Book book = books.get(0);
		Assertions.assertNotNull(book);
		Assertions.assertEquals(book.getName(), BOOK_NAME);
	}

	@Test
	@Order(8)
	public void shouldDoDeleteEntityWhenIdIsEmpty() {
		Assertions.assertThrows(
				RuntimeException.class,
				() -> authorService.delete(null),
				"author not found by id " + null);

		Assertions.assertDoesNotThrow(() -> authorService.delete("null"));

		Assertions.assertThrows(
				RuntimeException.class,
				() -> bookService.delete(null),
				"book not found by id " + null);

		Assertions.assertDoesNotThrow(() -> bookService.delete("null"));
	}
}
