package book.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> bookList;

    public Library(String name) {
        this.name = name;
        this.bookList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
