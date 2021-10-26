package ua.com.alevel.service;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Book;

public class BookService {

    private final BookDao bookDao = new BookDao();

    public void create(Book book) {
        bookDao.create(book);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void delete(String id) {
        bookDao.delete(id);
    }

    public Book findById(String id) {
        return bookDao.findById(id);
    }

    public ArrayListMy<Book> findAll() {
        return bookDao.findAll();
    }
}
