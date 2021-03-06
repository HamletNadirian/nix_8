package ua.com.alevel.dao;

import ua.com.alevel.arraylist.ArrayListMy;
import ua.com.alevel.entity.Book;
import ua.com.alevel.db.BookDB;

public class BookDao {

    public void create(Book book) {
        BookDB.getInstance().create(book);
    }

    public void update(Book book) {
        BookDB.getInstance().update(book);
    }

    public void delete(String id) {
        BookDB.getInstance().delete(id);
    }

    public Book findById(String id) {
        return BookDB.getInstance().findById(id);
    }

    public ArrayListMy<Book> findAll() {
        return BookDB.getInstance().findAll();
    }

}
