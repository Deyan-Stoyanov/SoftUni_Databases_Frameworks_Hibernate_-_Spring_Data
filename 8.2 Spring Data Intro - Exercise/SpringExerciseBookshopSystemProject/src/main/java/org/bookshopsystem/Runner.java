package org.bookshopsystem;

import org.bookshopsystem.enums.AgeRestriction;
import org.bookshopsystem.enums.EditionType;
import org.bookshopsystem.models.entities.Author;
import org.bookshopsystem.models.entities.Book;
import org.bookshopsystem.models.entities.Category;
import org.bookshopsystem.services.AuthorService;
import org.bookshopsystem.services.BookService;
import org.bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component

public class Runner implements CommandLineRunner {

    private static final String AUTHORS_RESOURCE_FILE = System.getProperty("user.dir") + "/src/main/resources/" +  "authors.txt";

    private static final String BOOKS_RESOURCE_FILE = System.getProperty("user.dir") + "/src/main/resources/" +  "books.txt";

    private static final String CATEGORIES_RESOURCE_FILE = System.getProperty("user.dir") + "/src/main/resources/" +  "categories.txt";

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public Runner(AuthorService authorService, BookService bookService, CategoryService categoryService) throws IOException {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        initAuthors();
        initCategories();
        initBooks();
        booksByDate();
        booksBefore();
        orderAuthorsByBookCount();
        booksOfAuthor();
    }

    private void initCategories() throws IOException {
        List<String> allCategories = Files.readAllLines(Paths.get(CATEGORIES_RESOURCE_FILE));
        List<Category> categories = getCategories(allCategories);
        categories.forEach(this.categoryService::addCategoryToDb);
    }

    private static List<Category> getCategories(List<String> list){
        return list.stream().filter(x -> !x.isEmpty()).map(c -> {
            Category category = new Category();
            category.setName(c);
            return category;
        }).collect(Collectors.toList());
    }

    private void initAuthors() throws IOException {
        List<String> allAuthors = Files.readAllLines(Paths.get(AUTHORS_RESOURCE_FILE));
        List<Author> authors =  getAuthors(allAuthors);
        authors.forEach(this.authorService::saveAuthorToDb);
    }

    private static List<Author> getAuthors(List<String> list){
        return list.stream().map(s -> {
            String[] names = s.split("\\s+");
            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            return author;
        }).collect(Collectors.toList());
    }

    private void initBooks() throws IOException, ParseException {
        List<String> allBooks = Files.readAllLines(Paths.get(BOOKS_RESOURCE_FILE));
        List<Author> allAuthors = this.authorService.getAll();
        List<Category> categories = this.categoryService.getAll();

        Random random = new Random();
        List<Book> allB = new ArrayList<>();
        for (String line : allBooks) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(allAuthors.size());
            Author author = allAuthors.get(authorIndex);
            int editionTypeIndex = Integer.parseInt(data[0]);
            EditionType[] editionTypes = EditionType.values();
            EditionType editionType = editionTypes[editionTypeIndex];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType.toString());
            book.setDateTime(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction.toString());
            book.setTitle(title);
            book.getCategories().add(categories.get(random.nextInt(categories.size())));
            allB.add(book);
        }

        allB.forEach(this.bookService::saveBookToDb);
    }

    private void booksByDate() throws ParseException {
        System.out.println("All books after year 2000: ");
        Date dateAfter = new SimpleDateFormat( "dd-MM-yyyy" ).parse( "01-01-2000" );
        this.bookService.findAllByDateTimeAfter(dateAfter).forEach(x -> System.out.println(x.getTitle()));
    }

    private void booksBefore() throws ParseException {
        System.out.println("All authors with a book before 1990: ");
        Date dateBefore = new SimpleDateFormat( "dd-MM-yyyy" ).parse( "01-01-1990" );
        this.bookService.findAllByDateTimeBefore(dateBefore).forEach(x -> System.out.println(x.getAuthor().getFirstName() + " " + x.getAuthor().getLastName()));
    }

    private void orderAuthorsByBookCount(){
        System.out.println("Author ordered by number of book descending: ");
        this.authorService.findAllByBooksOrderByBooksSizeDesc().forEach(x -> System.out.println(x.getFirstName() + " " + x.getLastName() + ", " + x.getBooks().size()));
    }

    private void booksOfAuthor(){
        System.out.println("All books by George Powell: ");
        this.bookService.findAllByAuthorOrderByDateTimeDescTitleAsc("George", "Powell").forEach(x -> System.out.println(x.getTitle() + ", " + x.getDateTime().toString() + ", " + x.getCopies()));
    }
}
