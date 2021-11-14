package ua.com.alevel.dao;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Book;

public interface BookDao extends BaseDao <Book>  {
	Book findBook(String name);
}
