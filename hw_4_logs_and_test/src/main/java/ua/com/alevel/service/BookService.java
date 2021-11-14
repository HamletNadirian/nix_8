package ua.com.alevel.service;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

public interface BookService extends BaseService<Book>{
	Book findAuthor(String id);
}
