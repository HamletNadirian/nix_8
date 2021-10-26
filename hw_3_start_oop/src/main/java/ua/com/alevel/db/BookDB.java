package ua.com.alevel.db;

import ua.com.alevel.array_list.ArrayListMy;
import ua.com.alevel.entity.Book;

import java.util.UUID;

public class BookDB {

    private ArrayListMy<Book> books;
    private static BookDB instance;

    private BookDB() {
        books = new ArrayListMy<>();
    }

    public static BookDB getInstance() {
        if (instance == null) {
            instance = new BookDB();
        }
        return instance;
    }

    public void create(Book book) {
        book.setId(generateId());
        books.add(book);
    }

    public void update(Book book) {
        Book current = findById(book.getId());
        current.setPrice(book.getPrice());
        current.setName(book.getName());
    }

    public void delete(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.remove(i);
            }
        }
    }

    public Book findById(String id) {
        int tempId = searchId(id);
        return books.get(tempId);
    }

    public int searchId(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                return (i);
            }
        }
        return -1;
    }

    public ArrayListMy<Book> findAll() {
        return books;
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
