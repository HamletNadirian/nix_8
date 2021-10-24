package ua.com.alevel.view;

import ua.com.alevel.array_list.ArrayListMy;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookController {
    
    private final BookService bookService = new BookService();
    
    public void run(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option");
        String position;
        try {
            runNavigation();
            while ((position=reader.readLine())!=null){
                crud(position,reader);
                if (position.equals("0")){
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create book, please enter 1");
        System.out.println("if you want update book, please enter 2");
        System.out.println("if you want delete book, please enter 3");
        System.out.println("if you want findById book, please enter 4");
        System.out.println("if you want findAll book, please enter 5");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }
    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" : create(reader); break;
            case "2" : update(reader); break;
            case "3" : delete(reader); break;
            case "4" : findById(reader); break;
            case "5" : findAll(reader); break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("UserController.create");
        try {
            System.out.println("Please, enter name book");
            String name = reader.readLine();
            System.out.println("Please, enter price book");
            String priceString = reader.readLine();
            int price = Integer.parseInt(priceString);
            Book book = new Book();
            book.setPrice(price);
            book.setName(name);
           bookService.create(book);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("UserController.update");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter name book");
            String name = reader.readLine();
            System.out.println("Please, enter price book");
            String priceString = reader.readLine();
            int price = Integer.parseInt(priceString);
            Book book = new Book();
            book.setId(id);
            book.setPrice(price);
            book.setName(name);
            bookService.update(book);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("BookController.delete");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            bookService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("BookController.findById");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            Book book = bookService.findById(id);
            System.out.println("user = " + book);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("BookController.findAll");
        ArrayListMy<Book> books = bookService.findAll();
        if (books != null && books.size() != 0) {
            /*for (Book book : books) {
                System.out.println("user = " + book);
            }*/
            for (int i = 0; i <books.size() ; i++) {
                System.out.println("book =" +books.get(i));
            }


        } else {
            System.out.println("users empty");
        }
    }
}
